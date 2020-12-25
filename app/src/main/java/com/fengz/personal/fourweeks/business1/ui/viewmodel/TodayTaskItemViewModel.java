package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

public class TodayTaskItemViewModel extends BaseViewModel {
    public ObservableField<TaskBean> bean = new ObservableField<>();
    public ObservableInt finishVisibility = new ObservableInt(View.GONE);

    public TodayTaskItemViewModel(@NonNull Application application, TaskBean task) {
        super(application);
        bean.set(task);
        setFinishVisibility(task);
    }

    private void setFinishVisibility(TaskBean task) {
        finishVisibility.set(task.getList().get(0).getDayStatus() == DayTaskStatus.FINISHED ? View.VISIBLE : View.GONE);
    }
}
