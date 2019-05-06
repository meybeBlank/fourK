package com.fengz.personal.fourweeks.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;

/**
 * 创建时间：2019/1/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：MVP BasePresenter
 */
public abstract class BasePresenter<V extends IView> implements LifecycleObserver, IPresenter<V> {

    private V mView;
    protected Context mContext;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void showCreateToast() {
        System.out.println("onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void showDestoryToast() {
        System.out.println("onDestory");
    }
}
