package com.fengz.personal.fourweeks.bingview;

import android.databinding.InverseMethod;

public class Converters {
    @InverseMethod("converStringToDouble")
    public static String convertDoubleToString(double txt) {
        return String.valueOf(txt);
    }

    public static double converStringToDouble(String txt) {
        try {
            return Double.parseDouble(txt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
