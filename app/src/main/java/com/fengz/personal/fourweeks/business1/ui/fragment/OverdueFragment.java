package com.fengz.personal.fourweeks.business1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.base.mvp.APresenter;
import com.fengz.personal.fourweeks.base.mvp.BaseFragment;
import com.fengz.personal.fourweeks.business1.contract.OverdueContract;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.business1.ui.Navigator;
import com.fengz.personal.fourweeks.business1.ui.adapter.OverDueAdapter;
import com.fengz.personal.fourweeks.common.MultipleRelativeLayout;

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
public class OverdueFragment extends BaseFragment implements OverdueContract.View<TaskBean> {

    @BindView(R.id.recycler_overdue_frg)
    YRecyclerView mRecycler;
    @BindView(R.id.mull_overdue_frg)
    MultipleRelativeLayout mMulLayout;

    private List<TaskBean> mData = new ArrayList<>();
    private OverDueAdapter mAdapter;
    private boolean created = false;

    @Inject
    @APresenter
    OverdueContract.Presenter mPresenter;
    @Inject
    Navigator mNavigator;

    public static OverdueFragment newInstance() {
        Bundle args = new Bundle();
        OverdueFragment fragment = new OverdueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_overdue, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        created = true;

        mMulLayout.showLoading();
        mPresenter.getData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!created) return;
        if (isVisibleToUser) {
            mMulLayout.showLoading();
            mPresenter.getData();
        }
    }

    @Override
    public void onDestroyView() {
        created = false;
        super.onDestroyView();
    }

    private void initUI() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new OverDueAdapter(mData,mNavigator);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setRefreshAndLoadMoreListener(new YRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData();
            }

            @Override
            public void onLoadMore() {
                mPresenter.loadmore();
            }
        });
    }

    @Override
    public void showErr(String msg) {
        // 本地数据一般没有问题
    }

    @Override
    public void setData(List<TaskBean> data) {
        mRecycler.setNoMoreData(false);
        mMulLayout.showContent();
        mRecycler.setReFreshComplete();
        if (data == null || data.size() < 1) {
            mMulLayout.showEmpty();
        } else {
            mData.clear();
            mData.addAll(data);
            mAdapter.notifyDataSetChanged();
            if (data.size() != PAGE_SIZE) {
                mRecycler.setNoMoreData(true);
            }
        }
    }

    @Override
    public void addData(List<TaskBean> data) {
        mRecycler.setloadMoreComplete();
        if (data == null || data.size() < 1) {
            mRecycler.setNoMoreData(true);
        } else {
            mData.addAll(data);
            mAdapter.notifyDataSetChanged();
            if (data.size() != PAGE_SIZE) {
                mRecycler.setNoMoreData(true);
            }
        }
    }
}
