package com.fengz.personal.fourweeks.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MenuItem;

import com.fengz.personal.fourweeks.BR;
import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.basemvvm.BaseActivity;
import com.fengz.personal.fourweeks.business1.ui.Navigator;
import com.fengz.personal.fourweeks.business1.ui.adapter.MainPageAdapter;
import com.fengz.personal.fourweeks.business1.ui.fragment.OverdueFragment;
import com.fengz.personal.fourweeks.business1.ui.view.ToolbarViewHolder;
import com.fengz.personal.fourweeks.business1.ui.viewmodel.MainActViewModel;
import com.fengz.personal.fourweeks.databinding.ActivityMainBinding;

import javax.inject.Inject;

/**
 * 创建时间：2019/4/1
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：注解界面包含三个子Fragment
 * {@link com.fengz.personal.fourweeks.business1.ui.fragment.TodayFragment}
 * {@link com.fengz.personal.fourweeks.business1.ui.fragment.ActivitingFragment}
 * {@link OverdueFragment}
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainActViewModel> {

    @Inject
    Navigator mNavigator;

    ToolbarViewHolder mToolbarView;

    public static Intent getCallingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbarHolder();
    }

    private void initToolbarHolder() {
        mToolbarView = new ToolbarViewHolder();
        mToolbarView.init(this, mBinding.includeToolbar.toolbarActionbar);
        mBinding.setVariable(BR.toolbarViewmodel, mToolbarView.getViewModel());
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initBaseUI() {
        mBinding.nvMainAct.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        mBinding.viewpagerMainAct.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        mBinding.viewpagerMainAct.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        setTitle("今日目标");
                        break;
                    case 1:
                        setTitle("当前目标");
                        break;
                    case 2:
                        setTitle("历史目标");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        setTitle("今日目标");
        mBinding.viewpagerMainAct.setCurrentItem(0);
        mBinding.viewpagerMainAct.setOffscreenPageLimit(3);
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerMainAct.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerMainAct.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setTitle(String title) {
        mToolbarView.getDataBinding().toolbarTitle.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_today:
                setTitle("今日目标");
                mBinding.drawerMainAct.closeDrawer(GravityCompat.START);
                mBinding.viewpagerMainAct.setCurrentItem(0);
                return true;
            case R.id.menu_activiting:
                setTitle("当前目标");
                mBinding.drawerMainAct.closeDrawer(GravityCompat.START);
                mBinding.viewpagerMainAct.setCurrentItem(1);
                return true;
            case R.id.menu_overdue:
                mBinding.drawerMainAct.closeDrawer(GravityCompat.START);
                setTitle("历史目标");
                mBinding.viewpagerMainAct.setCurrentItem(2);
                return true;
            case android.R.id.home:
                mBinding.drawerMainAct.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
