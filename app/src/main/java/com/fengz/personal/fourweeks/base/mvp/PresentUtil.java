package com.fengz.personal.fourweeks.base.mvp;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

/**
 * 创建时间：2019/1/3
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：反射的方式 通过注解{@link APresenter}绑定Presenter与View
 */
public class PresentUtil {

    interface ProcessRunnable {
        void run(Field field);
    }

    private static void process(Class clazz, ProcessRunnable runnable) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(APresenter.class) != null) {
                runnable.run(field);
            }
        }
    }

    static void inject(@NonNull final BaseActivity activity) {
        process(activity.getClass(), field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(activity);
                if (o instanceof IPresenter && o instanceof BasePresenter) {
                    IPresenter presenter = ((IPresenter) o);
                    BasePresenter bPresenter = ((BasePresenter) o);
                    presenter.attachView(activity);
                    presenter.setContext(activity);
                    activity.getLifecycle().addObserver(bPresenter);
                } else {
                    throw new IllegalArgumentException("This filed must extends BasePresenter");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    static void inject(@NonNull final BaseFragment fragment) {
        process(fragment.getClass(), field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(fragment);
                if (o instanceof IPresenter && o instanceof BasePresenter) {
                    IPresenter presenter = ((IPresenter) o);
                    BasePresenter bPresenter = ((BasePresenter) o);
                    presenter.attachView(fragment);
                    presenter.setContext(fragment.getContext());
                    fragment.getLifecycle().addObserver(bPresenter);
                } else {
                    throw new IllegalArgumentException("This filed must extends BasePresenter");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

}