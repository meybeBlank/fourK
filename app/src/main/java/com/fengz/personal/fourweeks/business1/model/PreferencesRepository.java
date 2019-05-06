package com.fengz.personal.fourweeks.business1.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 创建时间：2019/1/18
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：本地基本数据存储
 */
@Singleton
public class PreferencesRepository {

    private static final String TAB_LOGIN_INFO = "tab_login_info";
    private static final String PARA_USER_NAME = "para_user_name";
    private static final String PARA_PASSWORD = "para_password";
    private static final String PARA_LAST_LOGIN = "para_last_login";

    private Context mContext;

    @Inject
    public PreferencesRepository(Application context) {
        mContext = context;
    }

    /**
     * 保存登录记录
     */
    public boolean setLoginInfo(String userName, String password) {
        return mContext.getSharedPreferences(TAB_LOGIN_INFO, Context.MODE_PRIVATE)
                .edit()
                .putString(PARA_USER_NAME, userName)
                .putString(PARA_PASSWORD, password).commit();
    }

    /**
     * 获取登陆信息
     */
    public String[] getLoginInfo() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(TAB_LOGIN_INFO, Context.MODE_PRIVATE);
        String[] strings = new String[2];
        strings[0] = sharedPreferences.getString(PARA_USER_NAME, null);
        strings[1] = sharedPreferences.getString(PARA_PASSWORD, null);
        return strings;
    }

    /**
     * 保存上一次启动的时间
     */
    public boolean setLastLogin() {
        return mContext.getSharedPreferences(TAB_LOGIN_INFO, Context.MODE_PRIVATE)
                .edit()
                .putLong(PARA_LAST_LOGIN, System.currentTimeMillis())
                .commit();
    }

    /**
     * 获取上一次启动时间
     */
    public long getLastLogin() {
        return mContext.getSharedPreferences(TAB_LOGIN_INFO, Context.MODE_PRIVATE)
                .getLong(PARA_LAST_LOGIN, 0);
    }
}
