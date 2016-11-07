package com.lbsm.kdang.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lbsm.kdang.R;
import com.lbsm.kdang.base.BaseFragment;
import com.lbsm.kdang.ui.activities.PostingActivity;
import com.lbsm.kdang.ui.fragments.home.HomeTabFragmAdapter;
import com.lbsm.kdang.ui.fragments.home.PostEssenceFragm;
import com.lbsm.kdang.ui.fragments.home.PostFollowFragm;
import com.lbsm.kdang.ui.fragments.home.PostNewFragm;
import com.lbsm.kdang.widget.HomeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date: 2016/10/20.
 */

public class HomeFragm extends BaseFragment {

    @Bind(R.id.home_tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.home_viewpager)
    HomeViewPager mViewpager;
    @Bind(R.id.iv_posting)
    ImageView mPostingIv;

    private String[] mTitles;
    private List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTitles = getResources().getStringArray(R.array.home_tab_titles);
        mFragments = new ArrayList<>();
        mFragments.add(new PostEssenceFragm());
        mFragments.add(new PostNewFragm());
        mFragments.add(new PostFollowFragm());
        HomeTabFragmAdapter adapter = new HomeTabFragmAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @OnClick(R.id.iv_posting)
    public void onClick() {
        Intent intent =new Intent(this.getActivity(), PostingActivity.class);
        startActivity(intent);
    }
}
