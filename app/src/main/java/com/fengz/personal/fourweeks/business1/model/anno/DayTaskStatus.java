package com.fengz.personal.fourweeks.business1.model.anno;

import android.support.annotation.IntDef;

/**
 * 创建时间：2019/4/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：天任务状态
 */
@IntDef({DayTaskStatus.UNACTIVE, DayTaskStatus.ACTIVITING, DayTaskStatus.FINISHED, DayTaskStatus.FAIL})
public @interface DayTaskStatus {
    /**
     * 未开始任务
     */
    int UNACTIVE = -1;

    /**
     * 正在进行
     */
    int ACTIVITING = 0;

    /**
     * 完成的任务
     */
    int FINISHED = 1;

    /**
     * 失败的任务
     */
    int FAIL = 2;
}
