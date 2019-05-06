package com.fengz.personal.fourweeks.business1.presenter;

import com.fengz.personal.fourweeks.base.mvp.BasePresenter;
import com.fengz.personal.fourweeks.business1.contract.TodayTaskContract;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;
import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;

import javax.inject.Inject;

public class TodayTaskPresenter extends BasePresenter<TodayTaskContract.View> implements TodayTaskContract.Presenter {

    TaskRepository mRepository;

    @Inject
    public TodayTaskPresenter(TaskRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void finishTask(long taskId) {
        mRepository.updateDayTask(taskId, DayTaskStatus.FINISHED);
        getView().finishTask(taskId);
    }

    @Override
    public void getData() {
        getView().setData(mRepository.getTodayTask(System.currentTimeMillis()));
    }

    @Override
    public void loadmore() {
        // 这里没有上拉
    }

    @Override
    public void refreshData() {
        getView().setData(mRepository.getTodayTask(System.currentTimeMillis()));
    }
}
