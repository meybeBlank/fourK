package com.fengz.personal.fourweeks.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.base.mvp.APresenter;
import com.fengz.personal.fourweeks.basemvvm.BaseActivity;
import com.fengz.personal.fourweeks.business1.presenter.DetailsPresenter;
import com.fengz.personal.fourweeks.business1.ui.view.ToolbarViewHolder;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.DetailsViewModel;
import com.fengz.personal.fourweeks.databinding.ActivityDetailsBinding;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> {

    public static final String EXTRA_TASK_ID = "extra_task_id";

    public static Intent getCallingIntent(@NonNull Context context, long taskId) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }

    @Inject
    @APresenter
    DetailsPresenter mPresenter;

    private long mTaskId;

    @Override
    protected void initParam(Bundle savedInstanceState) {
        super.initParam(savedInstanceState);
        mTaskId = getIntent().getLongExtra(EXTRA_TASK_ID, -1);
    }

    @Override
    protected void initBaseUI() {
        ToolbarViewHolder holder = new ToolbarViewHolder();
        holder.init(this, mBinding.includeToolbar.toolbarActionbar);
        mBinding.setVariable(BR.toolbarViewmodel, holder.getViewModel());
        holder.setTitle("任务详情");
        holder.showBack(true);

        mViewModel.getData(mTaskId);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_details;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
