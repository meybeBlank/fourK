package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

public class TaskItemViewModel extends BaseViewModel {
    public ObservableField<TaskBean> bean = new ObservableField<>();

    public TaskItemViewModel(@NonNull Application application, TaskBean taskBean) {
        super(application);
        bean.set(taskBean);
    }
}
