package com.fengz.personal.fourweeks.business1.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;
import com.fengz.personal.fourweeks.business1.model.entity.DayTaskBean;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodayTaskAdapter extends RecyclerView.Adapter<TodayTaskAdapter.ViewHolder> {

    private List<TaskBean> mData;
    private ItemAdapterListener mListener;

    public TodayTaskAdapter(List<TaskBean> mData, ItemAdapterListener listener) {
        this.mData = mData;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_today, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.initUI(mData.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_rank_today_item)
        TextView mTvRankTodayItem;
        @BindView(R.id.tv_title_today_item)
        TextView mTvTitleTodayItem;
        @BindView(R.id.tv_content_today_item)
        TextView mTvContentTodayItem;
        @BindView(R.id.fl_finished_today_list)
        FrameLayout mFlFinishedTodayList;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void initUI(TaskBean task, int i) {
            mTvTitleTodayItem.setText(task.getTitle());
            mTvContentTodayItem.setText(task.getContent());
            if (task.getList().get(0).getDayStatus() == DayTaskStatus.ACTIVITING) {
                itemView.setOnClickListener(v -> mListener.onItemClick(i));
            } else {
                itemView.setOnClickListener(null);
            }
            try {
                DayTaskBean dayTaskBean = task.getList().get(0);
                mTvRankTodayItem.setText(dayTaskBean.getRank() + ".");
                mFlFinishedTodayList.setVisibility(dayTaskBean.getDayStatus() == DayTaskStatus.FINISHED
                        ? View.VISIBLE : View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
