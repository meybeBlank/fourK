package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.business1.ui.activity.AddActivity;

public class MainActViewModel extends BaseViewModel {

    public View.OnClickListener clickAddAct = view -> startActivity(AddActivity.class, new Bundle());

    public MainActViewModel(@NonNull Application application) {
        super(application);
    }
}
