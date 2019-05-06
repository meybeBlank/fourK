package com.fengz.personal.fourweeks.business1.presenter;

import com.fengz.personal.fourweeks.base.mvp.BasePresenter;
import com.fengz.personal.fourweeks.business1.contract.DetailsContract;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;

import javax.inject.Inject;

public class DetailsPresenter extends BasePresenter<DetailsContract.View> implements DetailsContract.Presenter {

    TaskRepository mRepository;

    @Inject
    public DetailsPresenter(TaskRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void getTask(long TaskId) {
        getView().setData(mRepository.getTaskDetails(TaskId));
    }
}
