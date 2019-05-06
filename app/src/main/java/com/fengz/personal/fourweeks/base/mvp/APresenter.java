package com.fengz.personal.fourweeks.base.mvp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间：2019/1/3
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：标记Presenter，{@link PresentUtil#inject(BaseActivity)}方法进行绑定
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APresenter {
}
