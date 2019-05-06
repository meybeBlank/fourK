package com.fengz.personal.fourweeks.business1.contract;

import com.fengz.personal.fourweeks.base.mvp.IPresenter;
import com.fengz.personal.fourweeks.base.mvp.IView;

/**
 * 创建时间：2019/3/25
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：新增 ideal
 */
public class AddContract {

    public interface View extends IView {
        void addSuccess(boolean success);
    }

    public interface Presenter extends IPresenter<View> {
        /**
         * 添加task
         *
         * @param title       标题
         * @param content     内容
         * @param usefullTime 可完成时段
         * @param desired     目标完成度
         * @param award       奖励
         * @param punishment  惩罚
         */
        void addTask(String title, String content, String usefullTime, String desired, String award, String punishment);
    }
}
