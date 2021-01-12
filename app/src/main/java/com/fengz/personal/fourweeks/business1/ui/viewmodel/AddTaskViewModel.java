package com.fengz.personal.fourweeks.business1.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.fengz.personal.fourweeks.basemvvm.BaseViewModel;
import com.fengz.personal.fourweeks.basemvvm.SingleLiveEvent;
import com.fengz.personal.fourweeks.business1.model.TaskRepository;
import com.fengz.personal.fourweeks.business1.model.anno.TaskStatus;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.utils.DateUtils;

public class AddTaskViewModel extends BaseViewModel {
    public SingleLiveEvent<String> snackbarEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> toastEvent = new SingleLiveEvent<>();
    public MutableLiveData<TaskBean> task = new MutableLiveData<>();
    public View.OnClickListener clickAddTask = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addTask();
        }
    };

    TaskRepository mTaskRepository;

    public AddTaskViewModel(@NonNull Application application) {
        super(application);
        task.setValue(new TaskBean());
        mTaskRepository = new TaskRepository();
    }

    private void addTask() {
        if (TextUtils.isEmpty(task.getValue().getTitle())) {
            snackbarEvent.setValue("标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(task.getValue().getContent())) {
            snackbarEvent.setValue("内容不能为空");
            return;
        }

        long cur = System.currentTimeMillis();
        task.getValue().setCreatedData(cur);
        task.getValue().setLifeData(28L * 24 * 60 * 60 * 1000);
        task.getValue().setEndData(DateUtils.getBeginDay(cur) + 28L * 24 * 60 * 60 * 1000);
        task.getValue().setStatus(TaskStatus.ACTIVITING);
        long num = mTaskRepository.addNewTask(task.getValue());
        if (num > 0) {
            showToast("添加成功");
            finish();
        } else {
            showToast("添加失败");
        }
    }
}
