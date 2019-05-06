package com.fengz.personal.fourweeks.business1.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fengz.personal.fourweeks.business1.ui.fragment.ActivitingFragment;
import com.fengz.personal.fourweeks.business1.ui.fragment.OverdueFragment;
import com.fengz.personal.fourweeks.business1.ui.fragment.TodayFragment;

import java.util.HashMap;

public class MainPageAdapter extends FragmentPagerAdapter {

    public static final int MAIN_SIZE = 3;
    private HashMap<Integer, Fragment> mFragmentHashMap = new HashMap<>();

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (mFragmentHashMap.containsKey(position)) {
            fragment = mFragmentHashMap.get(position);
        } else {
            switch (position) {
                case 0:
                    fragment = TodayFragment.newInstance();
                    break;
                case 1:
                    fragment = ActivitingFragment.newInstance();
                    break;
                case 2:
                    fragment = OverdueFragment.newInstance();
                    break;
                default:
                    break;
            }
            mFragmentHashMap.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return MAIN_SIZE;
    }
}
