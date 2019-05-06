package com.fengz.personal.fourweeks.http;

import com.fengz.personal.fourweeks.http.exception.ErrExceptionFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：提供 Rx转换，处理线程调度、生命周期绑定、数据脱壳 等转换
 */
public class TransformerHelper {

    /**
     * 创建时间：2019/1/2
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：转换基本类型
     */
    public static <T extends ResponseShuck<K>, K> ObservableTransformer<T, K> observableTransformer() {
        return upstream -> upstream.compose(TransformerHelper.schedulerTransformer())
                .compose(TransformerHelper.<T, K>transform());
    }

    /**
     * 创建时间：2018/11/6
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：脱壳，脱掉外层数据封装
     */
    public static <T extends ResponseShuck<K>, K> ObservableTransformer<T, K> transform() {
        return upstream -> upstream.flatMap((Function<T, ObservableSource<K>>) t -> {
            if (t.getCode() == 200 || t.getStatus() == 1) {
                return Observable.<K>just(t.getContent());
            } else {
                return Observable.error(ErrExceptionFactory.create(t));
            }
        });
    }

    /**
     * 创建时间：2018/11/6
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：线程调度
     */
    public static <T> ObservableTransformer<T, T> schedulerTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
