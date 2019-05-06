package com.fengz.personal.fourweeks.business1.model.anno;

import android.support.annotation.IntDef;

/**
 * 创建时间：2019/4/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：任务状态
 */
@IntDef({TaskStatus.ACTIVITING, TaskStatus.FINISHED, TaskStatus.CANCELLED})
public @interface TaskStatus {
    /**
     * 正在进行
     */
    int ACTIVITING = 0;

    /**
     * 完成的任务
     */
    int FINISHED = 1;

    /**
     * 取消的任务
     */
    int CANCELLED = 2;
}
