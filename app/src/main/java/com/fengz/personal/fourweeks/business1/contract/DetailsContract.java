package com.fengz.personal.fourweeks.business1.contract;

import com.fengz.personal.fourweeks.base.mvp.IPresenter;
import com.fengz.personal.fourweeks.base.mvp.IView;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

/**
 * 创建时间：2019/3/25
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：task 详情
 */
public class DetailsContract {

    public interface View extends IView {
        void setData(TaskBean task);
    }

    public interface Presenter extends IPresenter<View> {
        void getTask(long TaskId);
    }
}
