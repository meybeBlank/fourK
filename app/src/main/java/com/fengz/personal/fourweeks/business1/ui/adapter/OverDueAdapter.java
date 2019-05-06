package com.fengz.personal.fourweeks.business1.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.business1.ui.Navigator;

import java.text.NumberFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间：2019/4/24
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：历史任务列表Adapter
 */
public class OverDueAdapter extends RecyclerView.Adapter<OverDueAdapter.ViewHolder> {

    private List<TaskBean> mData;

    Navigator mNavigator;

    public OverDueAdapter(List<TaskBean> data,Navigator navigator) {
        mData = data;
        mNavigator = navigator;
    }

    @NonNull
    @Override
    public OverDueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_overdue, viewGroup, false);
        return new ViewHolder(inflate, mNavigator);
    }

    @Override
    public void onBindViewHolder(@NonNull OverDueAdapter.ViewHolder viewHolder, int position) {
        viewHolder.initUI(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_overdue_item)
        TextView mTvTitleOverdueItem;
        @BindView(R.id.tv_degree_overdue_item)
        TextView mTvDegreeOverdueItem;
        @BindView(R.id.tv_content_overdue_item)
        TextView mTvContentOverdueItem;

        Navigator navigator;

        ViewHolder(View view, Navigator navigator) {
            super(view);
            ButterKnife.bind(this, view);
            this.navigator = navigator;
        }

        void initUI(TaskBean task) {
            itemView.setOnClickListener(v ->
                    navigator.navigator2DetailsAct(itemView.getContext(), task.getId()));
            mTvTitleOverdueItem.setText(task.getTitle());
            mTvDegreeOverdueItem.setText("完成度：" + NumberFormat.getPercentInstance().format(task.getTaskPersents()));
            mTvContentOverdueItem.setText(task.getContent());
        }
    }
}
