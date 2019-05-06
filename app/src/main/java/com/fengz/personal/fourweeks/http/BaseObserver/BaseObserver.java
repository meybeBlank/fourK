package com.fengz.personal.fourweeks.http.BaseObserver;

import com.fengz.personal.fourweeks.http.exception.ErrExceptionFactory;
import com.fengz.personal.fourweeks.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：处理返回数据结果
 */
public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onComplete() {
        onAfter();
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        onBefore(disposable);
    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    @Override
    public void onError(Throwable e) {
        String msg = ErrExceptionFactory.create(e);
        ToastUtils.show(e.getMessage());
        onErrors(msg);
        onAfter();
    }

    public void onBefore(Disposable disposable) {

    }

    public void onAfter() {

    }

    public abstract void onErrors(String e);

    public abstract void onResponse(T response);
}