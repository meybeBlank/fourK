package com.fengz.personal.fourweeks.business1.presenter;


import com.fengz.personal.fourweeks.base.mvp.BasePresenter;
import com.fengz.personal.fourweeks.business1.contract.OverdueContract;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;

import javax.inject.Inject;

import static com.fengz.personal.fourweeks.base.Constants.PAGE_SIZE;

public class OverduePresenter extends BasePresenter<OverdueContract.View> implements OverdueContract.Presenter {

    TaskRepository mResitory;

    private long timeAnchor = Long.MAX_VALUE;
    private int page = 0;

    @Inject
    public OverduePresenter(TaskRepository mResitory) {
        this.mResitory = mResitory;
    }

    @Override
    public void getData() {
        timeAnchor = System.currentTimeMillis();
        page = 0;
        getView().setData(mResitory.getTaskByStatus(0, PAGE_SIZE, timeAnchor, false));
    }

    @Override
    public void loadmore() {
        page++;
        getView().addData(mResitory.getTaskByStatus(page * PAGE_SIZE, PAGE_SIZE, timeAnchor, false));
    }

    @Override
    public void refreshData() {
        timeAnchor = System.currentTimeMillis();
        page = 0;
        getView().setData(mResitory.getTaskByStatus(0, PAGE_SIZE, timeAnchor, false));
    }
}
