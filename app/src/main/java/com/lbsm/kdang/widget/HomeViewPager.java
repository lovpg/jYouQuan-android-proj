package com.lbsm.kdang.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * date: 2016/10/21.
 */

public class HomeViewPager extends ViewPager {

    private float downX;
    private float upX;
    private float touchSlop;
    private boolean isLeftSlide = false;

    public HomeViewPager(Context context) {
        this(context,null);
    }

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop=getTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                upX = event.getX();
                if (upX - downX < -touchSlop)
                    isLeftSlide = true;
                break;

        }
        if (getCurrentItem() == 2 && isLeftSlide){
            isLeftSlide=false;
            return false;
        }
        return super.onTouchEvent(event);
    }

    private float getTouchSlop() {
        return ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

}
