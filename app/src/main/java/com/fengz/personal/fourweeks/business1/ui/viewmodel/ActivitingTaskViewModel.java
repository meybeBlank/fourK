package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.basemvvm.SingleLiveEvent;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.business1.ui.activity.DetailsActivity;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

import static com.fengz.personal.fourweeks.business1.ui.activity.DetailsActivity.EXTRA_TASK_ID;

public class ActivitingTaskViewModel extends BaseViewModel {

    public ObservableList<TaskItemViewModel> observableList = new ObservableArrayList<>();
    public OnItemBind<TaskItemViewModel> itemBinding = new OnItemBind<TaskItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, TaskItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_overdue)
                    .bindExtra(BR.onItemClick, onItemClick);
        }
    };
    public OnItemClick<TaskItemViewModel> onItemClick = bean -> {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_TASK_ID, bean.bean.get().getId());
        startActivity(DetailsActivity.class, bundle);
    };

    TaskRepository mRepository;

    public SingleLiveEvent<Integer> showStatus = new SingleLiveEvent<>();

    public ActivitingTaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository();
    }

    public void getData() {
        List<TaskBean> task = mRepository.getTaskByStatus(0, Integer.MAX_VALUE, System.currentTimeMillis(), true);
        observableList.clear();
        showStatus.setValue(task.size() > 0 ? 1 : 2);
        for (TaskBean bean :
                task) {
            observableList.add(new TaskItemViewModel(getApplication(), bean));
        }
    }
}
