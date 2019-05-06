package com.fengz.personal.fourweeks.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.base.mvp.BaseActivity;
import com.fengz.personal.fourweeks.business1.contract.MainContract;
import com.fengz.personal.fourweeks.business1.ui.Navigator;
import com.fengz.personal.fourweeks.business1.ui.adapter.MainPageAdapter;
import com.fengz.personal.fourweeks.business1.ui.fragment.OverdueFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间：2019/4/1
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：注解界面包含三个子Fragment
 * {@link TodayFragment} {@link ActivitingFragment} {@link OverdueFragment}
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.nv_main_act)
    NavigationView mNavigation;
    @BindView(R.id.drawer_main_act)
    DrawerLayout mDrawer;
    @BindView(R.id.viewpager_main_act)
    ViewPager mViewPager;

    @Inject
    Navigator mNavigator;

    public static Intent getCallingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("今日目标");
        showHomeAsUp(false);
        setHomeAsUp(R.drawable.ic_toc_black);

        initUI();
    }

    private void initUI() {
        mNavigation.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        mViewPager.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_today:
                setTitle("今日目标");
                mDrawer.closeDrawer(GravityCompat.START);
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.menu_activiting:
                setTitle("当前目标");
                mDrawer.closeDrawer(GravityCompat.START);
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.menu_overdue:
                mDrawer.closeDrawer(GravityCompat.START);
                setTitle("历史目标");
                mViewPager.setCurrentItem(2);
                return true;
            case android.R.id.home:
                mDrawer.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(value = {R.id.fab_add_task_main_act})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_task_main_act:
                mNavigator.navigator2AddTaskAct(this);
                break;
        }
    }
}
