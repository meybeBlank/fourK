package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;
import com.fengz.personal.fourweeks.business1.model.entity.DayTaskBean;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

import java.util.Arrays;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class DetailsViewModel extends BaseViewModel {

    private TaskRepository mReq;
    public MutableLiveData<TaskBean> taskBean = new MutableLiveData<>();

    public ObservableList<DayItemViewModel> observableList = new ObservableArrayList<>();
    public OnItemBind<DayItemViewModel> itemBinding = (itemBinding, position, item) -> itemBinding.set(BR.viewModel, position >= 7 ? R.layout.item_block : R.layout.item_week);

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        mReq = new TaskRepository();
    }

    public void getData(long TaskId) {
        TaskBean task = mReq.getTaskDetails(TaskId);
        taskBean.setValue(task);

        observableList.clear();
        List<String> weeks = Arrays.asList("一", "二", "三", "四", "五", "六", "七");
        for (String weekInfo :
                weeks) {
            observableList.add(new DayItemViewModel(getApplication(), weekInfo));
        }
        for (DayTaskBean dayTask :
                task.getList()) {
            observableList.add(new DayItemViewModel(getApplication(), dayTask));
        }
    }
}
