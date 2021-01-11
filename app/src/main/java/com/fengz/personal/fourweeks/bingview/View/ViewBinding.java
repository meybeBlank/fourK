package com.fengz.personal.fourweeks.bingview.View;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;

public class ViewBinding {

    @BindingAdapter(value = {"bind:visibleOrGone"})
    public static void visibleOrGone(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value = {"bind:dayStatus"})
    public static void dayStatus(View view, int dayStatus) {
        Context context = view.getContext();
        switch (dayStatus) {
            case DayTaskStatus.UNACTIVE:
            case DayTaskStatus.FAIL:
                view.setBackground(ContextCompat.getDrawable(context, R.color.day_unactivite));
                break;
            case DayTaskStatus.ACTIVITING:
                view.setBackground(ContextCompat.getDrawable(context, R.color.day_activiting));
                break;
            case DayTaskStatus.FINISHED:
                view.setBackground(ContextCompat.getDrawable(context, R.color.day_finish));
                break;
        }
    }
}
