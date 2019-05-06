package com.fengz.personal.fourweeks.business1.contract;

import com.fengz.personal.fourweeks.base.mvp.IPresenter;
import com.fengz.personal.fourweeks.base.mvp.IView;

/**
 * 创建时间：2019/3/25
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：首页 当日ideal
 */
public class MainContract {

    public interface View extends IView {
    }

    public interface Presenter extends IPresenter<View> {
    }
}
