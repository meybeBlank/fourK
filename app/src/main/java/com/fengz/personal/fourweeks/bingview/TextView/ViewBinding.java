package com.fengz.personal.fourweeks.bingview.TextView;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.fengz.personal.fourweeks.utils.DateUtils;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ViewBinding {
    @BindingAdapter(value = {"bind:startDate", "bind:endDate"}, requireAll = true)
    public static void setDateTxt(TextView textView, long statrDate, long endDate) {
        textView.setText(DateUtils.parse(statrDate, DateUtils.FORMAT_1)
                + " —— "
                + DateUtils.parse(endDate, DateUtils.FORMAT_1));
    }

    @BindingAdapter(value = {"bind:degree","bind:desired"})
    public static void setDegree(TextView textView, double degree, double desired){
        textView.setText(new DecimalFormat("0.00").format(100 * degree)
                + "/" + desired);
    }
}
