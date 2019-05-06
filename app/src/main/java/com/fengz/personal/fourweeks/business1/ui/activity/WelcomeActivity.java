package com.fengz.personal.fourweeks.business1.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fengz.personal.fourweeks.base.mvp.BaseActivity;
import com.fengz.personal.fourweeks.business1.model.PreferencesRepository;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;
import com.fengz.personal.fourweeks.business1.ui.Navigator;
import com.fengz.personal.fourweeks.utils.DateUtils;
import com.fengz.personal.fourweeks.utils.LogUtils;

import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity {

    @Inject
    Navigator mNavigator;
    @Inject
    PreferencesRepository mPer;
    @Inject
    TaskRepository mRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long lastLogin = mPer.getLastLogin();
        if (lastLogin <= DateUtils.getBeginDay()) {
            LogUtils.info(LogUtils.TAG_DB, "每日更新数据库！！！");
            mRepository.updateDB();
        }
        mNavigator.navigator2MainAct(this);
        finish();
    }
}
