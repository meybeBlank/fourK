package com.fengz.personal.fourweeks.basemvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fengz.personal.fourweeks.business1.ui.activity.AddActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BaseViewModel extends AndroidViewModel implements IViewModel {

    // rxLifecycle
    private WeakReference<LifecycleProvider> mLifecycle;

    private LiveDataHolder mUIHolder = new LiveDataHolder();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    //region 传递RxLifecycle 处理Rx依赖View已经销毁等情况
    public void injectLifecycleProvider(LifecycleProvider<Lifecycle.Event> lifecycleProvider) {
        mLifecycle = new WeakReference<>(lifecycleProvider);
    }

    public LifecycleProvider getLifecycle() {
        return mLifecycle.get();
    }

    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return mLifecycle.get().bindToLifecycle();
    }
    //endregion

    //region 观察绑定View的生命周期
    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
    //endregion

    /**
     * 如果VM被Model持有，View销毁后 onCleared()通知释放Model销毁不必要的持有
     * <p>
     * Activity销毁时ViewModelStore调用方法，
     * 特例：Activity旋转onDestory不会调用，隐藏HolderFragment通过setRetainInstance(true);方式保存ViewModel
     */
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    //region LiveData基础界面事件绑定

    /**
     * 获取界面LiveData, 绑定到LifecyclerOwner
     */
    public LiveDataHolder getUIHolder() {
        return mUIHolder;
    }

    /**
     * 页面基础UI事件LiveData封装
     */
    public final class LiveDataHolder{
        private SingleLiveEvent<Boolean> showLoadingEvent;
        private SingleLiveEvent<Void> dismissLoadingEvent;
        private SingleLiveEvent<Void> finishActEvent;
        private SingleLiveEvent<Map<String, Object>> startActEvent;

        public SingleLiveEvent<Boolean> getShowLoadingEvent() {
            return showLoadingEvent = get(showLoadingEvent);
        }

        public SingleLiveEvent<Void> getDismissLoadingEvent() {
            return dismissLoadingEvent = get(dismissLoadingEvent);
        }

        public SingleLiveEvent<Void> getFinishActEvent() {
            return finishActEvent = get(finishActEvent);
        }

        public SingleLiveEvent<Map<String, Object>> getStartActEvent() {
            return startActEvent = get(startActEvent);
        }

        private <T> SingleLiveEvent<T> get(SingleLiveEvent<T> liveData) {
            if (liveData == null) {
                liveData = new SingleLiveEvent<>();
            }
            return liveData;
        }
    }

    protected void finish() {
        mUIHolder.getFinishActEvent().call();
    }

    protected void startActivity(Class<?> classAct, Bundle bundle) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(ParamKey.CLASS.value, classAct);
        map.put(ParamKey.BUNDLE.value, bundle);
        mUIHolder.getStartActEvent().setValue(map);
    }

    public enum ParamKey {
        CLASS("key_class"),
        BUNDLE("key_bundle");

        public String value;

        ParamKey(String key) {
            value = key;
        }
    }
    //endregion
}
