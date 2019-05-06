package com.fengz.personal.fourweeks.utils;

import android.util.Log;

import com.fengz.personal.fourweeks.BuildConfig;

/**
 * 创建时间：2019/3/12
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：log打印工具类
 * {@link LogUtils#LEVEL} 设置当前Log打印重要等级
 * {@link LogUtils#RELEASE_SHOW} release是否打印日志
 */
public class LogUtils {

    public static final String TAG_DB = "tagdb";

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;

    private static final int LEVEL = BuildConfig.LOG_DEBUG_LEVEL;
    private static final boolean RELEASE_SHOW = BuildConfig.LOG_RELEASE_SHOW;

    private static boolean isShow() {
        return BuildConfig.DEBUG || RELEASE_SHOW;
    }

    /**
     * 打印Verbose级别日志
     */
    public static void verbose(String tag, String msg) {
        if (isShow() && LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    /**
     * 打印Debug级别日志
     */
    public static void debug(String tag, String msg) {
        if (isShow() && LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 打印Info级别日志
     */
    public static void info(String tag, String msg) {
        if (isShow() && LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印Warn级别日志
     */
    public static void warn(String tag, String msg) {
        if (isShow() && LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印Error级别的日志
     */
    public static void error(String tag, String msg) {
        if (isShow() && LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

}
