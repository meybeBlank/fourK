package com.fengz.personal.fourweeks.basemvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengz.personal.fourweeks.business1.ui.widget.LoadingDialog;
import com.fengz.personal.fourweeks.utils.LogUtils;
import com.fengz.personal.fourweeks.utils.ToastUtils;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

import static com.fengz.personal.fourweeks.utils.LogUtils.TAG_BASE;

/**
 * 适合于单个ViewModel作为主要VM,多个VM需要自己新增
 *
 * @param <V>  DataBinding
 * @param <VM> ViewModel
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatDialogFragment
        implements IView {

    // onCreat绑定 onDestory后不再发送数据
    private final LifecycleProvider<Lifecycle.Event> PROVIDER =
            AndroidLifecycle.createLifecycleProvider(this);

    protected V mBinding;
    protected VM mViewModel;

    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, initContentView(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initParam(savedInstanceState);
        initViewViewModel();
        observeBaseLiveData();
        initUIData();
    }

    //region onCreate执行初始化操作

    /**
     * 初始化页面传递参数
     */
    protected void initParam(Bundle savedInstanceState) {
        LogUtils.info(TAG_BASE, "需要的时候重写方法，初始化页面传递参数");
    }

    /**
     * 绑定DataBinding、ViewModel和View,以及基础初始化
     */
    protected void initViewViewModel() {
        LogUtils.info(TAG_BASE, "绑定DataBinding、ViewModel和View,以及基础初始化");

        mViewModel = initViewModel();

        // 绑定V+VM
        mBinding.setVariable(initVariableId(), mViewModel);
        // 使用 LiveData 将数据变化通知给界面
        mBinding.setLifecycleOwner(this);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(mViewModel);
        mViewModel.injectLifecycleProvider(PROVIDER);
    }

    /**
     * 绑定VM中LiveData数据到LifecycleOwner
     */
    protected void observeBaseLiveData() {
        // 绑定基础事件
        mViewModel.getUIHolder().getShowLoadingEvent().observe(this, this::showLoadingB);
        mViewModel.getUIHolder().getDismissLoadingEvent().observe(this, aVoid -> dismissLoadingB());
        mViewModel.getUIHolder().getFinishActEvent().observe(this, aVoid -> getActivity().finish());
        mViewModel.getUIHolder().getToastActEvent().observe(this, ToastUtils::show);
        mViewModel.getUIHolder().getStartActEvent().observe(this, stringObjectMap -> {
            if (stringObjectMap == null) throw new NullPointerException("stringObjectMap is null");
            Class<?> aClass = (Class<?>) stringObjectMap.get(BaseViewModel.ParamKey.CLASS.value);
            Bundle bundle = (Bundle) stringObjectMap.get(BaseViewModel.ParamKey.BUNDLE.value);
            startActivity(aClass, bundle);
        });

        // 扩展LiveData事件
        observeLiveData();
    }

    /**
     * 扩展LiveData
     */
    protected void observeLiveData() {
        LogUtils.info(TAG_BASE, "需要的时候重写方法，扩展LiveData");
    }

    /**
     * 初始化界面基础信息
     */
    protected void initUIData() {
        LogUtils.info(TAG_BASE, "需要的时候重写方法，初始化界面基础信息");
    }

    /**
     * 设置界面LayoutRes
     */
    @LayoutRes
    protected abstract int initContentView();

    /**
     * 返回 ViewModel id
     */
    protected abstract int initVariableId();

    @Override
    public void onDestroy() {
        if (mBinding != null) {
            mBinding.unbind();
            mBinding = null;
        }
        super.onDestroy();
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
        return (VM) ViewModelProviders.of(this).get(modelClass);
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
            mLoadingDialog = new LoadingDialog(Objects.requireNonNull(getContext()));
        }
        mLoadingDialog.show();
        mLoadingDialog.setContent("");
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(cancelable);
    }

    public void showLoadingB(String msg, boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(Objects.requireNonNull(getContext()));
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
        Intent intent = new Intent(getActivity(), aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //endregion
}

