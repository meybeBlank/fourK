package com.fengz.personal.fourweeks.http;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：生命周期状态，用于绑定Activity/Fragment与Rxjava网络流
 * 避免内存泄漏
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.SOURCE)
@IntDef({Lifecycle.CREATE, Lifecycle.START, Lifecycle.RESUME,
        Lifecycle.PAUSE, Lifecycle.STOP, Lifecycle.DESTROY})
public @interface Lifecycle {
    int CREATE = 0x100;
    int START = 0x101;
    int RESUME = 0x102;
    int PAUSE = 0x103;
    int STOP = 0x104;
    int DESTROY = 0x105;
}
