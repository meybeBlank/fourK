package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.business1.model.entity.DayTaskBean;

public class DayItemViewModel extends BaseViewModel {
    public MutableLiveData<DayTaskBean> bean;
    public MutableLiveData<String> week;

    public DayItemViewModel(@NonNull Application application, DayTaskBean task) {
        super(application);
        bean = new MutableLiveData<>();
        bean.setValue(task);
    }

    public DayItemViewModel(@NonNull Application application, String weekInfo) {
        super(application);
        this.week = new MutableLiveData<>();
        week.setValue(weekInfo);
    }
}
