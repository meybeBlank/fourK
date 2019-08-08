package com.fengz.personal.fourweeks.base;

import android.app.Application;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;

import com.fengz.personal.fourweeks.db.DaoMaster;
import com.fengz.personal.fourweeks.db.DaoSession;
import com.fengz.personal.fourweeks.di.DaggerAppComponent;
import com.fengz.personal.fourweeks.utils.NotificationAManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * 创建时间：2018/12/24
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：dagger-android 基础实现
 */
public class MyApplication extends DaggerApplication {

    private static MyApplication mInstance;
    private DaoSession mDaoSession;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initGreenDao();
        NotificationAManager.get().init();
    }

    /**
     * 初始化GreenDao
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "four_weeks.db");
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static Application getContext() {
        if (mInstance == null) mInstance = new MyApplication();
        return mInstance;
    }

    public static MyApplication get(){
        return mInstance;
    }
}
