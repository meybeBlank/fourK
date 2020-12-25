package com.fengz.personal.fourweeks.business1.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseFragment;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.TodayTaskViewModel;
import com.fengz.personal.fourweeks.databinding.FragmentTodayTaskBinding;
import com.fengz.personal.fourweeks.utils.DialogManager;

public class TodayFragment extends BaseFragment<FragmentTodayTaskBinding, TodayTaskViewModel> {

    private boolean created = false;

    public static TodayFragment newInstance() {
        Bundle args = new Bundle();
        TodayFragment fragment = new TodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        mViewModel.showStatus.setValue(0);
        mViewModel.getData();
        created = true;
    }

    @Override
    protected void observeLiveData() {
        mViewModel.showFinish.observe(this, bean -> showConfirmDialog(bean));
        mViewModel.showStatus.observe(this, status -> {
            if (status == 0) {
                mBinding.mullTodayFrg.showLoading();
            } else if (status == 1) {
                mBinding.mullTodayFrg.showContent();
//                mBinding.recyclerTodayFrg.setReFreshComplete();
            } else {
                mBinding.mullTodayFrg.showEmpty();
            }
        });
        mViewModel.refresh.observe(this, refresh -> {
            mBinding.refreshTodayFrg.setRefreshing(refresh);
        });
    }

    @Override
    protected int initContentView() {
        return R.layout.fragment_today_task;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onDestroyView() {
        created = false;
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!created) return;
        if (isVisibleToUser) {
            mViewModel.showStatus.setValue(0);
            mViewModel.getData();
        }
    }

    private void initUI() {
        mBinding.refreshTodayFrg.setOnRefreshListener(() -> {
            mViewModel.refresh.setValue(true);
            mViewModel.getData();
        });
    }

    private void showConfirmDialog(TaskBean bean) {
        AlertDialog dialog = DialogManager.createAlertDialog(getContext(), true,
                null, "确认完成任务？",
                "确认", "取消",
                (dialog1, which) -> mViewModel.finishTask(bean.getList().get(0).getId()),
                (dialog12, which) -> dialog12.dismiss());
        dialog.show();
    }
}
