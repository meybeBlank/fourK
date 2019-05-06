package com.fengz.personal.fourweeks.utils;

import android.widget.Toast;

import com.fengz.personal.fourweeks.base.MyApplication;

import java.lang.ref.WeakReference;

public class ToastUtils {
    // 简单缓存Toast
    private static WeakReference<Toast> mWeakReferenceToast = null;

    public static void show(String msg) {
        showSingleToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 单例toast 多个toast即时刷新
     *
     * @param msg
     * @param duraion
     */
    public static void showSingleToast(String msg, int duraion) {
        try {
            Toast singleToast = null;
            if (mWeakReferenceToast == null || (singleToast = mWeakReferenceToast.get()) == null) {
                singleToast = Toast.makeText(MyApplication.getContext(), "", duraion);
                mWeakReferenceToast = new WeakReference(singleToast);
            }
            singleToast.setText(msg);
            singleToast.setDuration(duraion);
            singleToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
