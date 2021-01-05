package com.fengz.personal.fourweeks.business1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.base.mvp.APresenter;
import com.fengz.personal.fourweeks.basemvvm.BaseFragment;
import com.fengz.personal.fourweeks.business1.contract.OverdueContract;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.business1.ui.Navigator;
import com.fengz.personal.fourweeks.business1.ui.adapter.OverDueAdapter;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.OverdueTaskViewModel;
import com.fengz.personal.fourweeks.common.MultipleRelativeLayout;
import com.fengz.personal.fourweeks.databinding.FragmentOverdueBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import yuan.kuo.yu.view.YRecyclerView;

import static com.fengz.personal.fourweeks.base.Constants.PAGE_SIZE;

/**
 * 创建时间：2019/4/24
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：过期任务列表
 */
public class OverdueFragment extends BaseFragment<FragmentOverdueBinding, OverdueTaskViewModel> {

    private boolean created = false;

    public static OverdueFragment newInstance() {
        Bundle args = new Bundle();
        OverdueFragment fragment = new OverdueFragment();
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
        return R.layout.fragment_overdue;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void observeLiveData() {
        mViewModel.showStatus.observe(this, integer -> mBinding.mullOverdueFrg.show(integer));
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
