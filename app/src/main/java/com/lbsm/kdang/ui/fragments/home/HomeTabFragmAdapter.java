package com.lbsm.kdang.ui.fragments.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * date: 2016/10/21.
 */

public class HomeTabFragmAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private String[] mTitles;

    public HomeTabFragmAdapter(FragmentManager fm, List<Fragment> fragments,String[] titles) {
        super(fm);
        mFragments=fragments;
        mTitles=titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
