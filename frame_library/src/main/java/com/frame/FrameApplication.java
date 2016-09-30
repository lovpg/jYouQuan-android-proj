package com.frame;

import android.app.Application;

/**
 * Created by Administrator on 2016/9/28.
 */
public class FrameApplication extends Application {

    public static FrameApplication instance;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }

    public static FrameApplication getInstance() {
        return instance;
    }

}
