package com.fengz.personal.fourweeks.base.mvp;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.business1.ui.widget.LoadingDialog;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 创建时间：2019/1/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity
        implements HasFragmentInjector, HasSupportFragmentInjector, IView {

    public final LifecycleProvider<Lifecycle.Event> PROVIDER = AndroidLifecycle.createLifecycleProvider(this);

    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;
    private Unbinder unbinder;

    private Toolbar mActionBarToolbar;
    private TextView mToolbarTitle;

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 兼容未采用di的Activity
        try {
            AndroidInjection.inject(this);
        } catch (Exception e) {
        }
        // 对xml文件的View进行预处理
//        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflaterConvert());
        super.onCreate(savedInstanceState);

        // 注入Presenter
        PresentUtil.inject(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
        getActionBarToolbar();
    }

    /**
     * 是否展示返回上级按钮
     *
     * @param showHomeAsUp 是否展示
     */
    protected void showHomeAsUp(boolean showHomeAsUp) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }

    protected void setHomeAsUp(@DrawableRes int iconId){
        if (mActionBarToolbar == null) {
            mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
        }
        if (mActionBarToolbar != null) {
            mActionBarToolbar.setNavigationIcon(iconId);
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mActionBarToolbar != null) {
            mToolbarTitle.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                mActionBarToolbar.setNavigationIcon(R.drawable.icon_arrow_gray_left);
                setSupportActionBar(mActionBarToolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                mToolbarTitle = mActionBarToolbar.findViewById(R.id.toolbar_title);
            }
        }
        return mActionBarToolbar;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

    @Override
    public LifecycleProvider<Lifecycle.Event> getLifecycleProvider() {
        return PROVIDER;
    }

    public void showLoadingB(boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(cancelable);
    }


    public void showLoadingB(String msg, boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
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

    private void cancelLoadingB(){
        dismissLoadingB();
        if(mLoadingDialog != null){
            mLoadingDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        cancelLoadingB();
        super.onDestroy();
    }
}
