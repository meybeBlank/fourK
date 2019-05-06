package com.fengz.personal.fourweeks.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.AndroidSupportInjection;

/**
 * 创建时间：2019/1/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：BaseFragment
 */
public class BaseFragment extends Fragment implements HasFragmentInjector, IView {
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> childFragmentInjector;

    private final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        try {
            AndroidSupportInjection.inject(this);
        } catch (Exception e) {
        }
        super.onAttach(context);
        PresentUtil.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return childFragmentInjector;
    }

    @Override
    public LifecycleProvider<Lifecycle.Event> getLifecycleProvider() {
        return provider;
    }
}
