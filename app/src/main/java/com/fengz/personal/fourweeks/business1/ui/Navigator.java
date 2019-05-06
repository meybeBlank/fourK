package com.fengz.personal.fourweeks.business1.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.business1.ui.activity.AddActivity;
import com.fengz.personal.fourweeks.business1.ui.activity.DetailsActivity;
import com.fengz.personal.fourweeks.business1.ui.activity.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 创建时间：2019/1/21
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：页面跳转导航
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigator2MainAct(@NonNull Context context) {
        Intent intent = MainActivity.getCallingIntent(context);
        context.startActivity(intent);
    }

    public void navigator2AddTaskAct(@NonNull Context context) {
        Intent intent = AddActivity.getCallingIntent(context);
        context.startActivity(intent);
    }

    public void navigator2DetailsAct(@NonNull Context context, long taskId) {
        Intent intent = DetailsActivity.getCallingIntent(context, taskId);
        context.startActivity(intent);
    }

}
