package com.lbsm.kdang.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lbsm.kdang.R;
import com.lbsm.kdang.ui.fragments.HomeFragm;
import com.lbsm.kdang.ui.fragments.MainTabFragmAdapter;
import com.lbsm.kdang.ui.fragments.MeFragm;
import com.lbsm.kdang.ui.fragments.MessageFragm;
import com.lbsm.kdang.widget.MainViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final int TAB_HOME = 0;
    private final int TAB_MESSAGE = 1;
    private final int TAB_ME = 2;

    @Bind(R.id.main_viewpager)
    MainViewPager mViewPager;
    @Bind(R.id.main_tab_home_imgbtn)
    ImageButton mTabHomeImgbtn;
    @Bind(R.id.main_tab_home)
    LinearLayout mTabHome;
    @Bind(R.id.main_tab_message_imgbtn)
    ImageButton mTabMessageImgbtn;
    @Bind(R.id.main_tab_message)
    LinearLayout mTabMessage;
    @Bind(R.id.main_tab_me_imgbtn)
    ImageButton mTabMeImgbtn;
    @Bind(R.id.main_tab_me)
    LinearLayout mTabMe;
    @Bind(R.id.main_tab_home_tv)
    TextView mTabHomeTv;
    @Bind(R.id.main_tab_message_tv)
    TextView mTabMessageTv;
    @Bind(R.id.main_tab_me_tv)
    TextView mTabMeTv;

    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getColor(R.color.red_200), 50);
        ButterKnife.bind(this);

        initView();
        setSelect(TAB_HOME);
    }

    private void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragm());
        mFragments.add(new MessageFragm());
        mFragments.add(new MeFragm());
        MainTabFragmAdapter adapter = new MainTabFragmAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //                int currentItem=mViewPager.getCurrentItem();
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.main_tab_home, R.id.main_tab_message, R.id.main_tab_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_home:
                setSelect(TAB_HOME);
                break;
            case R.id.main_tab_message:
                setSelect(TAB_MESSAGE);
                break;
            case R.id.main_tab_me:
                setSelect(TAB_ME);
                break;
        }
    }

    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i) {
        resetTab();
        switch (i) {
            case TAB_HOME:
                mTabHomeImgbtn.setImageResource(R.drawable.tab_home_selected);
                mTabHomeTv.setTextColor(getColor(R.color.colorPrimary));
                break;
            case TAB_MESSAGE:
                mTabMessageImgbtn.setImageResource(R.drawable.tab_message_selected);
                mTabMessageTv.setTextColor(getColor(R.color.colorPrimary));
                break;
            case TAB_ME:
                mTabMeImgbtn.setImageResource(R.drawable.tab_me_selected);
                mTabMeTv.setTextColor(getColor(R.color.colorPrimary));
                break;
        }

    }

    private void resetTab() {
        mTabHomeImgbtn.setImageResource(R.drawable.tab_home);
        mTabHomeTv.setTextColor(getColor(R.color.gray_main_tab_text));
        mTabMessageImgbtn.setImageResource(R.drawable.tab_message);
        mTabMessageTv.setTextColor(getColor(R.color.gray_main_tab_text));
        mTabMeImgbtn.setImageResource(R.drawable.tab_me);
        mTabMeTv.setTextColor(getColor(R.color.gray_main_tab_text));
    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }


}
