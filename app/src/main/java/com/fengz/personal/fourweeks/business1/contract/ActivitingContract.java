package com.fengz.personal.fourweeks.business1.contract;

/**
 * 创建时间：2019/3/25
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：有效 ideal
 */
public class ActivitingContract {

    public interface View<T> extends BaseListContract.View<T> {
    }

    public interface Presenter extends BaseListContract.Presenter<View> {
    }
}
