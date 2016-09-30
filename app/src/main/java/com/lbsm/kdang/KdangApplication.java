package com.lbsm.kdang;

import android.graphics.Bitmap;

import com.baidu.mapapi.SDKInitializer;
import com.frame.FrameApplication;
import com.frame.data.preference.FrameDataConfig;
import com.frame.util.FileUtil;
import com.lbsm.kdang.picture.LocalImageHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;

/**
 * Created by Administrator on 2016/9/28.
 */
public class KdangApplication extends FrameApplication {

    private static KdangApplication instance;
    private FrameDataConfig config;
    public LocalImageHelper localImageHelper;
    public File mPicFolder = null;
    public DisplayImageOptions options;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        init();
    }

    public static KdangApplication getInstance() {
        return instance;
    }

    private void init() {
        // TODO Auto-generated method stub
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getApplicationContext());
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
        options = imageOptions();
        mPicFolder = FileUtil.avatarFile(instance);
        if(!mPicFolder.exists())mPicFolder.mkdirs();
        localImage();
        SDKInitializer.initialize(this);
    }

    public DisplayImageOptions imageOptions() {
        // TODO Auto-generated method stub
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

    public void localImage(){
        if(localImageHelper == null)localImageHelper = new LocalImageHelper(this);
        localImageHelper.startLocalImage();
    }



    public FrameDataConfig getConfig() {
        return config == null ? config = new FrameDataConfig(this) : config;
    }
}
