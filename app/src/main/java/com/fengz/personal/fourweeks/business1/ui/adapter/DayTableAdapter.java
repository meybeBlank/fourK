package com.fengz.personal.fourweeks.business1.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;
import com.fengz.personal.fourweeks.business1.model.entity.DayTaskBean;

import java.util.Arrays;
import java.util.List;

public class DayTableAdapter extends RecyclerView.Adapter {
    private List<DayTaskBean> mData;

    public DayTableAdapter(List<DayTaskBean> list) {
        mData = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= 7) {
            return super.getItemViewType(position);
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == 1) {
            return new WeekHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_week, viewGroup, false));
        } else {
            return new BlockHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_block, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        List<String> weeks = Arrays.asList("一", "二", "三", "四", "五", "六", "七");
        View itemView = viewHolder.itemView;
        if (getItemViewType(i) == 1) {
            ((TextView) itemView).setText(weeks.get(i));
        } else {
            DayTaskBean bean = mData.get(i - 7);
            switch (bean.getDayStatus()) {
                case DayTaskStatus.UNACTIVE:
                case DayTaskStatus.FAIL:
                    itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.color.day_unactivite));
                    break;
                case DayTaskStatus.ACTIVITING:
                    itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.color.day_activiting));
                    break;
                case DayTaskStatus.FINISHED:
                    itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.color.day_finish));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.size() < 1) ? 0 : mData.size() + 7;
    }

    class WeekHolder extends RecyclerView.ViewHolder {
        public WeekHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class BlockHolder extends RecyclerView.ViewHolder {

        public BlockHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
