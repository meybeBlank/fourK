package com.fengz.personal.fourweeks.business1.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseFragment;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.business1.ui.adapter.TodayTaskAdapter;
import com.fengz.personal.fourweeks.utils.DialogManager;

import java.util.ArrayList;
import java.util.List;

import yuan.kuo.yu.view.YRecyclerView;

public class TodayFragment extends BaseFragment {

    private List<TaskBean> mData = new ArrayList<>();
    private TodayTaskAdapter mAdapter;
    private boolean created = false;

    public static TodayFragment newInstance() {
        Bundle args = new Bundle();
        TodayFragment fragment = new TodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initUI();

//        mMulLayout.showLoading();
//        mPresenter.getData();
        created = true;
    }

    @Override
    protected int initContentView() {
        return 0;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        created = false;
        super.onDestroyView();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (!created) return;
//        if (isVisibleToUser) {
//            mMulLayout.showLoading();
//            mPresenter.getData();
//        }
//    }
//
//    private void initUI() {
//        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(
//                2, StaggeredGridLayoutManager.VERTICAL));
//        mAdapter = new TodayTaskAdapter(mData,
//                position -> showConfirmDialog(mData.get(position)));
//        mRecycler.setLoadMoreEnabled(false);
//        mRecycler.setAdapter(mAdapter);
//        mRecycler.setRefreshAndLoadMoreListener(
//                new YRecyclerView.OnRefreshAndLoadMoreListener() {
//                    @Override
//                    public void onRefresh() {
//                        mPresenter.refreshData();
//                    }
//
//                    @Override
//                    public void onLoadMore() {
//                    }
//                });
//    }
//
//    private void showConfirmDialog(TaskBean bean) {
//        AlertDialog dialog = DialogManager.createAlertDialog(getContext(), true,
//                null, "确认完成任务？",
//                "确认", "取消",
//                (dialog1, which) -> mPresenter.finishTask(bean.getList().get(0).getId()),
//                (dialog12, which) -> dialog12.dismiss());
//        dialog.show();
//    }
//
//    @Override
//    public void finishTask(long dayId) {
//        mPresenter.getData();
//    }
//
//    @Override
//    public void showErr(String msg) {
//        // 本地数据一般没有问题
//    }
//
//    @Override
//    public void setData(List<TaskBean> data) {
//        mMulLayout.showContent();
//        mRecycler.setReFreshComplete();
//        if (data == null || data.size() < 1) {
//            mMulLayout.showEmpty();
//        } else {
//            mData.clear();
//            mData.addAll(data);
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void addData(List<TaskBean> data) {
//        // 没有上拉
//    }
}
