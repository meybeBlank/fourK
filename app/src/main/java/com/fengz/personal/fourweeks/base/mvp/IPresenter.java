package com.fengz.personal.fourweeks.base.mvp;

import android.content.Context;

/**
 * 创建时间：2019/1/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：MVP IPresenter
 */
public interface IPresenter<T extends IView> {

    void attachView(T view);

    T getView();

    void setContext(Context context);

    Context getContext();
}
