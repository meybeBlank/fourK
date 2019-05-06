package com.fengz.personal.fourweeks.business1.contract;

import com.fengz.personal.fourweeks.base.mvp.IPresenter;
import com.fengz.personal.fourweeks.base.mvp.IView;

import java.util.List;

/**
 * 创建时间：2019/4/25
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：常见的上下拉 数据加载 页面基础Contract
 */
public class BaseListContract {
    public interface View<T> extends IView {
        void showErr(String msg);
        void setData(List<T> data);
        void addData(List<T> data);
    }

    public interface Presenter<T extends IView> extends IPresenter<T> {
        void getData();

        void loadmore();

        void refreshData();
    }
}
