package com.fengz.personal.fourweeks.business1.contract;

import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

import java.util.List;

public class TodayTaskContract {
    public interface View extends BaseListContract.View<TaskBean> {
        void finishTask(long dayId);
    }

    public interface Presenter extends BaseListContract.Presenter<View> {
        void finishTask(long dayId);
    }
}
