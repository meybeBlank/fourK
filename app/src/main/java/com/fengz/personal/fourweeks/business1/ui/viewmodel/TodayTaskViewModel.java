package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.basemvvm.SingleLiveEvent;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;
import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class TodayTaskViewModel extends BaseViewModel {

    public SingleLiveEvent<TaskBean> showFinish = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> refresh = new SingleLiveEvent<>();
    // 放到xml里面
    public SingleLiveEvent<Integer> showStatus = new SingleLiveEvent<>();

    TaskRepository mRepository;

    public TodayTaskViewModel(@NonNull Application application) {
        super(application);
        // 按理来说应该单例的。。。。
        mRepository = new TaskRepository();
    }

    //给RecyclerView添加ObservableList
    public ObservableList<TodayTaskItemViewModel> observableList = new ObservableArrayList<>();
    //RecyclerView多布局添加ItemBinding
    public OnItemBind<TodayTaskItemViewModel> itemBinding = new OnItemBind<TodayTaskItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, TodayTaskItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_today)
                    .bindExtra(BR.onItemClick, onItemClick);
        }
    };
    public OnItemClick<TodayTaskItemViewModel> onItemClick = new OnItemClick<TodayTaskItemViewModel>() {
        @Override
        public void onClick(TodayTaskItemViewModel bean) {
            TaskBean taskBean = bean.bean.get();
            if (taskBean.getList().get(0).getDayStatus() != DayTaskStatus.FINISHED) {
                showFinish.setValue(taskBean);
            }
        }
    };
//    public ItemBinding<TodayTaskItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_today);

    public void finishTask(long taskId) {
        mRepository.updateDayTask(taskId, DayTaskStatus.FINISHED);
        getData();
    }

    public void getData() {
        List<TaskBean> task = mRepository.getTodayTask(System.currentTimeMillis());
        showStatus.setValue(task.size() > 0 ? 1 : 2);
        refresh.setValue(false);
        observableList.clear();
        for (TaskBean bean :
                task) {
            observableList.add(new TodayTaskItemViewModel(getApplication(), bean));
        }
    }
}
