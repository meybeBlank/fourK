package com.fengz.personal.fourweeks.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import com.fengz.personal.fourweeks.R;

/**
 * 创建时间：2018/11/17
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：工具类
 */
public class ScreenUtils {
    public static void setFullScreen(Context context, boolean full) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (full) {
                if (Build.VERSION.SDK_INT >= 21) {
                    View decorView = activity.getWindow().getDecorView();
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                } else {
                    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            } else {
                //隐藏
                if (Build.VERSION.SDK_INT >= 21) {
                    View decorView = activity.getWindow().getDecorView();
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_VISIBLE);
                    activity.getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.transparent));
                } else {
                    activity.getWindow().setFlags(View.SYSTEM_UI_FLAG_VISIBLE, View.SYSTEM_UI_FLAG_VISIBLE);
                }
            }
        }
    }
}
