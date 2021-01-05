package com.fengz.personal.fourweeks.business1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseFragment;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.ActivitingTaskViewModel;
import com.fengz.personal.fourweeks.databinding.FragmentActivitingBinding;

/**
 * 创建时间：2019/4/3
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：进行中任务
 */
public class ActivitingFragment extends BaseFragment<FragmentActivitingBinding, ActivitingTaskViewModel> {

    private boolean created = false;

    public static ActivitingFragment newInstance() {
        Bundle args = new Bundle();
        ActivitingFragment fragment = new ActivitingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        created = true;
    }

    @Override
    protected int initContentView() {
        return R.layout.fragment_activiting;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void observeLiveData() {
        mViewModel.showStatus.observe(this, integer -> mBinding.mulActivitingeFrg.show(integer));
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

    @Override
    public void onDestroyView() {
        created = false;
        super.onDestroyView();
    }
}
