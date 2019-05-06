package com.fengz.personal.fourweeks.business1.presenter;

import android.text.TextUtils;

import com.fengz.personal.fourweeks.base.mvp.BasePresenter;
import com.fengz.personal.fourweeks.business1.contract.AddContract;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;
import com.fengz.personal.fourweeks.business1.model.anno.TaskStatus;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.utils.DateUtils;

import javax.inject.Inject;

public class AddPresenter extends BasePresenter<AddContract.View> implements AddContract.Presenter {

    TaskRepository mTaskRepository;

    @Inject
    public AddPresenter(TaskRepository taskRepository) {
        this.mTaskRepository = taskRepository;
    }

    @Override
    public void addTask(String title, String content, String usefullTime, String desired, String award, String punishment) {
        long cur = System.currentTimeMillis();
        TaskBean taskBean = new TaskBean();
        taskBean.setTitle(title);
        taskBean.setContent(content);
        taskBean.setCreatedData(cur);
        taskBean.setLifeData(28L * 24 * 60 * 60 * 1000);
        taskBean.setEndData(DateUtils.getBeginDay(cur) + 28L * 24 * 60 * 60 * 1000);
        taskBean.setUsefullTime(usefullTime);
        if (!TextUtils.isEmpty(desired)) {
            taskBean.setDesiredPersents(Double.valueOf(desired));
        }
        taskBean.setAward(award);
        taskBean.setPunishment(punishment);
        taskBean.setStatus(TaskStatus.ACTIVITING);
        long num = mTaskRepository.addNewTask(taskBean);

        getView().addSuccess(num >= 0);
    }
}
