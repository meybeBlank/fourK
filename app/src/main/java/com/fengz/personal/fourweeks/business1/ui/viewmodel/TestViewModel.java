package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;

public class TestViewModel extends BaseViewModel {
    public ObservableField<String> text = new ObservableField<>("test");

    public TestViewModel(@NonNull Application application) {
        super(application);
    }
}
