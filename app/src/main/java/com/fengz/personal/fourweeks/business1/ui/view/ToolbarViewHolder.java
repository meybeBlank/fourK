package com.fengz.personal.fourweeks.business1.ui.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.basemvvm.BaseView;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.ToolbarViewModel;
import com.fengz.personal.fourweeks.databinding.ToolbarActionbarBinding;

public class ToolbarViewHolder extends BaseView<ToolbarActionbarBinding, ToolbarViewModel> {

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void init(AppCompatActivity activity, View parent) {
        super.init(activity, parent);
        activity.setSupportActionBar((Toolbar) parent);

    }
}