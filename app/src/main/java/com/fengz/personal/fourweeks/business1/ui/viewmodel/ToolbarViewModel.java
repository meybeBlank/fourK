package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;

public class ToolbarViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableInt showBack = new ObservableInt(View.GONE);

    public View.OnClickListener backClick = view ->
            getUIHolder().getFinishActEvent().call();

    public ToolbarViewModel(@NonNull Application application) {
        super(application);
    }
}
