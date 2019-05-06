package com.fengz.personal.fourweeks.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.fengz.personal.fourweeks.business1.model.entity.DayTaskBean;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

import com.fengz.personal.fourweeks.db.DayTaskBeanDao;
import com.fengz.personal.fourweeks.db.TaskBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dayTaskBeanDaoConfig;
    private final DaoConfig taskBeanDaoConfig;

    private final DayTaskBeanDao dayTaskBeanDao;
    private final TaskBeanDao taskBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dayTaskBeanDaoConfig = daoConfigMap.get(DayTaskBeanDao.class).clone();
        dayTaskBeanDaoConfig.initIdentityScope(type);

        taskBeanDaoConfig = daoConfigMap.get(TaskBeanDao.class).clone();
        taskBeanDaoConfig.initIdentityScope(type);

        dayTaskBeanDao = new DayTaskBeanDao(dayTaskBeanDaoConfig, this);
        taskBeanDao = new TaskBeanDao(taskBeanDaoConfig, this);

        registerDao(DayTaskBean.class, dayTaskBeanDao);
        registerDao(TaskBean.class, taskBeanDao);
    }
    
    public void clear() {
        dayTaskBeanDaoConfig.clearIdentityScope();
        taskBeanDaoConfig.clearIdentityScope();
    }

    public DayTaskBeanDao getDayTaskBeanDao() {
        return dayTaskBeanDao;
    }

    public TaskBeanDao getTaskBeanDao() {
        return taskBeanDao;
    }

}