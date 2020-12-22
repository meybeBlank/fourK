package com.fengz.personal.fourweeks.basemvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fengz.personal.fourweeks.business1.ui.widget.LoadingDialog;
import com.fengz.personal.fourweeks.utils.LogUtils;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.fengz.personal.fourweeks.utils.LogUtils.TAG_BASE;

/**
 * 依赖于AppCompatActivity的子View
 * 观察了父Act的生命周期
 */
public abstract class BaseView<V extends ViewDataBinding, VM extends BaseViewModel>
        implements IView, LifecycleObserver {

    // onCreat绑定 onDestory后不再发送数据
    private LifecycleProvider<Lifecycle.Event> PROVIDER;

    protected V mBinding;
    protected VM mViewModel;

    protected View parent;
    protected AppCompatActivity activity;

    private LoadingDialog mLoadingDialog;

    public void init(AppCompatActivity activity, View parent) {
        this.activity = activity;
        this.parent = parent;
        PROVIDER = AndroidLifecycle.createLifecycleProvider(activity);
        activity.getLifecycle().addObserver(this);

        initParam();
        initViewViewModel();
        observeBaseLiveData();
        initUIData();
    }

    public V getDataBinding() {
        return mBinding;
    }

    public VM getViewModel() {
        return mViewModel;
    }

    //region onCreate执行初始化操作

    /**
     * 初始化页面传递参数
     */
    protected void initParam() {
        LogUtils.info(TAG_BASE, "需要的时候重写方法，初始化页面传递参数");
    }

    /**
     * 绑定DataBinding、ViewModel和View,以及基础初始化
     */
    protected void initViewViewModel() {
        LogUtils.info(TAG_BASE, "绑定DataBinding、ViewModel和View,以及基础初始化");

        mViewModel = initViewModel();
        mBinding = DataBindingUtil.bind(parent);


        // 绑定V+VM
        mBinding.setVariable(initVariableId(), mViewModel);
        // 使用 LiveData 将数据变化通知给界面
        mBinding.setLifecycleOwner(activity);
        //让ViewModel拥有View的生命周期感应
        activity.getLifecycle().addObserver(mViewModel);
        mViewModel.injectLifecycleProvider(PROVIDER);
    }

    /**
     * 绑定VM中LiveData数据到LifecycleOwner
     */
    protected void observeBaseLiveData() {
        // 绑定基础事件
//        mViewModel.getUIHolder().getShowLoadingEvent().observe(mLifecycleOwner, this::showLoadingB);
//        mViewModel.getUIHolder().getDismissLoadingEvent().observe(mLifecycleOwner, aVoid -> dismissLoadingB());
//        mViewModel.getUIHolder().getFinishActEvent().observe(mLifecycleOwner, aVoid -> finish());
//        mViewModel.getUIHolder().getStartActEvent().observe(mLifecycleOwner, stringObjectMap -> {
//            if (stringObjectMap == null) throw new NullPointerException("stringObjectMap is null");
//            Class<?> aClass = (Class<?>) stringObjectMap.get(BaseViewModel.ParamKey.CLASS.value);
//            Bundle bundle = (Bundle) stringObjectMap.get(BaseViewModel.ParamKey.BUNDLE.value);
//            startActivity(aClass, bundle);
//        });

        // 扩展LiveData事件
        observeLiveData();
    }

    /**
     * 扩展LiveData
     */
    private void observeLiveData() {
        LogUtils.info(TAG_BASE, "需要的时候重写方法，扩展LiveData");
    }

    /**
     * 初始化界面基础信息
     */
    protected void initUIData() {
        LogUtils.info(TAG_BASE, "需要的时候重写方法，初始化界面基础信息");
    }

    /**
     * 返回 ViewModel id
     */
    protected abstract int initVariableId();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (mBinding != null) {
            mBinding.unbind();
            mBinding = null;
        }
    }

    /**
     * 初始化ViewModel 生成泛型ViewModel
     * 需要修改mViewModel 覆盖重写方法
     */
    protected VM initViewModel() {
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            modelClass = BaseViewModel.class;
        }
        return createViewModel(modelClass);
    }

    private VM createViewModel(Class modelClass) {
        return (VM) ViewModelProviders.of(activity).get(modelClass);
    }
    //endregion

    /**
     * rxLifecycle 生命周期绑定
     */
    protected LifecycleTransformer<Lifecycle.Event> bindToLifecycle() {
        return PROVIDER.bindToLifecycle();
    }

    //region 基础的页面操作
    public void showLoadingB(boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(activity);
        }
        mLoadingDialog.show();
        mLoadingDialog.setContent("");
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(cancelable);
    }

    public void showLoadingB(String msg, boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(activity);
        }
        mLoadingDialog.show();
        mLoadingDialog.setContent(msg);
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(cancelable);
    }

    public void dismissLoadingB() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    private void startActivity(Class aClass, Bundle bundle) {
        Intent intent = new Intent(activity, aClass);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
//endregion
}
