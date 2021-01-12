package com.fengz.personal.fourweeks.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseActivity;
import com.fengz.personal.fourweeks.business1.ui.view.ToolbarViewHolder;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.AddTaskViewModel;
import com.fengz.personal.fourweeks.databinding.ActivityAddBinding;
import com.fengz.personal.fourweeks.utils.ToastUtils;

import java.util.Objects;

/**
 * 作   者：fengzhen
 * 功能描述：添加新任务
 */
public class AddActivity extends BaseActivity<ActivityAddBinding, AddTaskViewModel> {

    public static Intent getCallingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, AddActivity.class);
        return intent;
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_add;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initBaseUI() {
        super.initBaseUI();
        ToolbarViewHolder holder = new ToolbarViewHolder();
        holder.init(this, mBinding.includeToolbar.toolbarActionbar);
        mBinding.setVariable(BR.toolbarViewmodel, holder.getViewModel());
        holder.setTitle("添加任务");
        holder.showBack(true);
    }

    @Override
    protected void observeLiveData() {
        super.observeLiveData();
        mViewModel.snackbarEvent.observe(this, s ->
                Snackbar.make(mBinding.fabAddAct, Objects.requireNonNull(s), Snackbar.LENGTH_SHORT).show());
        mViewModel.toastEvent.observe(this, ToastUtils::show);
    }
}
