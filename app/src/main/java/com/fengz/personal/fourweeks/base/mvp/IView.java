package com.fengz.personal.fourweeks.base.mvp;

import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * 创建时间：2019/1/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：MVP IView
 */
public interface IView {

    LifecycleProvider<Lifecycle.Event> getLifecycleProvider();
}
