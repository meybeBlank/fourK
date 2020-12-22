package com.fengz.personal.fourweeks.business1.ui.fragment;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseFragment;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.TestViewModel;
import com.fengz.personal.fourweeks.databinding.FragmentTestBinding;

public class TestFragment extends BaseFragment<FragmentTestBinding, TestViewModel> {
    @Override
    protected int initContentView() {
        return R.layout.fragment_test;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}
