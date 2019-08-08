package com.fengz.personal.fourweeks.business1.model;

import com.fengz.personal.fourweeks.base.MyApplication;
import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;
import com.fengz.personal.fourweeks.business1.model.anno.TaskStatus;
import com.fengz.personal.fourweeks.business1.model.entity.DayTaskBean;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.db.DaoSession;
import com.fengz.personal.fourweeks.db.DayTaskBeanDao;
import com.fengz.personal.fourweeks.db.TaskBeanDao;
import com.fengz.personal.fourweeks.utils.DateUtils;
import com.fengz.personal.fourweeks.utils.LogUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 创建时间：2019/4/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：task db 操作管理类
 */
@Singleton
public class TaskRepository {

    private TaskBeanDao mTaskDao;
    private DayTaskBeanDao mDayDao;

    @Inject
    public TaskRepository() {
        DaoSession daoSession = MyApplication.get().getDaoSession();
        mDayDao = daoSession.getDayTaskBeanDao();
        mTaskDao = daoSession.getTaskBeanDao();
    }

    public long addNewTask(TaskBean task) {
        long insert = mTaskDao.insert(task);
        if (insert > 0) {
            TaskBean bean = mTaskDao.load(insert);
            addDayTask(bean.getId(), bean.getCreatedData());
        }
        return insert;
    }

    /**
     * 添加子任务表数据
     *
     * @param id 任务id
     */
    private void addDayTask(long id, long createDate) {
        long beginDay = DateUtils.getBeginDay();
        for (int i = 0; i < 28; i++) {
            DayTaskBean bean = new DayTaskBean();
            bean.setRank(i + 1);
            bean.setTaskID(id);
            bean.setCreateDate(beginDay);
            beginDay += 24 * 60 * 60 * 1000;
            bean.setCloseDate(beginDay);
            bean.setDayStatus(DayTaskStatus.UNACTIVE);
            long insert = mDayDao.insert(bean);
            LogUtils.info(LogUtils.TAG_DB, "插入行号：" + insert + "\\n" + bean.toString());
        }
    }

    /**
     * 创建时间：2019/4/28
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：获取全部任务列表，进行分页处理
     */
    public List<TaskBean> getAllTasks(int start, int length, long timeAnchor) {
        return mTaskDao.queryBuilder()
                .where(TaskBeanDao.Properties.CreatedData.lt(timeAnchor))
                .limit(length)
                .offset(start)
                .orderDesc(TaskBeanDao.Properties.CreatedData)
                .list();
    }

    /**
     * 创建时间：2019/4/28
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：根据任务状态获取任务列表，进行分页处理
     */
    public List<TaskBean> getTaskByStatus(int start, int length, long timeAnchor, boolean activiting) {
        return mTaskDao.queryBuilder()
                .where(TaskBeanDao.Properties.CreatedData.lt(timeAnchor),
                        activiting ? TaskBeanDao.Properties.EndData.ge(System.currentTimeMillis()) : TaskBeanDao.Properties.EndData.lt(System.currentTimeMillis()))
                .limit(length)
                .offset(start)
                .orderDesc(TaskBeanDao.Properties.CreatedData)
                .list();
    }

    /**
     * 创建时间：2019/4/28
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：根据任务Id获取任务详细信息
     */
    public TaskBean getTaskDetails(long id) {
        TaskBean taskBean = mTaskDao.queryBuilder()
                .where(TaskBeanDao.Properties.Id.eq(id))
                .orderDesc(TaskBeanDao.Properties.CreatedData)
                .unique();
        List<DayTaskBean> dayList = mDayDao.queryBuilder()
                .where(DayTaskBeanDao.Properties.TaskID.eq(id))
                .list();
        if (taskBean != null && dayList.size() > 0) {
            taskBean.setList(dayList);
            return taskBean;
        } else {
            return null;
        }
    }

    /**
     * 创建时间：2019/4/29
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：返回当日待完成的任务
     */
    public List<TaskBean> getTodayTask(long todayDate) {
        long beginDay = DateUtils.getBeginDay() + 1;
        List<TaskBean> taskBeans = mTaskDao.queryBuilder()
                .where(TaskBeanDao.Properties.Status.eq(TaskStatus.ACTIVITING))
                .orderDesc(TaskBeanDao.Properties.CreatedData)
                .list();
        for (TaskBean task :
                taskBeans) {
            DayTaskBean bean = mDayDao.queryBuilder()
                    .where(DayTaskBeanDao.Properties.TaskID.eq(task.getId()),
                            DayTaskBeanDao.Properties.CreateDate.le(beginDay),
                            DayTaskBeanDao.Properties.CloseDate.ge(beginDay))
                    .unique();
            if (bean.getDayStatus() == DayTaskStatus.UNACTIVE) {
                bean.setDayStatus(DayTaskStatus.ACTIVITING);
            }
            task.setList(Arrays.asList(bean));
        }
        return taskBeans;
    }

    /**
     * 创建时间：2019/4/29
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：更新当日任务状态
     */
    public void updateDayTask(long dayId, @DayTaskStatus int status) {
        DayTaskBean unique = mDayDao.queryBuilder()
                .where(DayTaskBeanDao.Properties.Id.eq(dayId))
                .unique();
        unique.setDayStatus(status);
        unique.setFinishedTime(DateUtils.parse(System.currentTimeMillis(), DateUtils.FORMAT_2));
        mDayDao.updateInTx(unique);

        // 更新百分比完成度
        updateTaskPersent(unique.getTaskID());
    }

    /**
     * 创建时间：2019/4/30
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：更新主任务完成度
     */
    public void updateTaskPersent(long taskId) {
        TaskBean taskBean = mTaskDao.queryBuilder()
                .where(TaskBeanDao.Properties.Id.eq(taskId))
                .unique();

        long count = mDayDao.queryBuilder()
                .where(DayTaskBeanDao.Properties.TaskID.eq(taskId),
                        DayTaskBeanDao.Properties.DayStatus.eq(DayTaskStatus.FINISHED))
                .count();

        taskBean.setTaskPersents(count / 28.0);
        mTaskDao.updateInTx(taskBean);
    }

    /**
     * 创建时间：2019/4/30
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：整表更新，每日0点 或者是当日第一次打开app时进行更新
     */
    public int[] updateDB() {
        int[] result = new int[3];
        long beginDay = DateUtils.getBeginDay() + 1;
//        long yesterday = beginDay - 24L*60*60*60*1000;

        // 更新前一天没完成的所有任务
        List<DayTaskBean> actDayList = mDayDao.queryBuilder()
                .where(DayTaskBeanDao.Properties.CloseDate.le(beginDay),
                        DayTaskBeanDao.Properties.DayStatus.eq(DayTaskStatus.UNACTIVE))
                .list();
        for (DayTaskBean day :
                actDayList) {
            day.setDayStatus(DayTaskStatus.FAIL);
        }
        mDayDao.updateInTx(actDayList);
        result[0] = actDayList.size();
        LogUtils.info(LogUtils.TAG_DB, "昨天未完成的任务条数： " + actDayList.size());

        // 更新所有今日任务状态
        List<DayTaskBean> dayList = mDayDao.queryBuilder()
                .where(DayTaskBeanDao.Properties.CreateDate.le(beginDay),
                        DayTaskBeanDao.Properties.CloseDate.ge(beginDay))
                .list();
        for (DayTaskBean day :
                dayList) {
            day.setDayStatus(DayTaskStatus.ACTIVITING);
        }
        result[1] = dayList.size();
        LogUtils.info(LogUtils.TAG_DB, "今日待完成的任务条数： " + dayList.size());

        // 更新主任务状态
        List<TaskBean> taskList = mTaskDao.queryBuilder()
                .where(TaskBeanDao.Properties.Status.eq(TaskStatus.ACTIVITING),
                        TaskBeanDao.Properties.EndData.le(beginDay))
                .list();
        for (TaskBean task :
                taskList) {
            task.setStatus(TaskStatus.FINISHED);
        }
        mTaskDao.updateInTx(taskList);
        result[2] = taskList.size();
        LogUtils.info(LogUtils.TAG_DB, "今日过期的任务条数： " + taskList.size());

        return result;
    }
}
