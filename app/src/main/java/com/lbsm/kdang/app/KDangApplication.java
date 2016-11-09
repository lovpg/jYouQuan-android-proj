package com.lbsm.kdang.app;

import android.graphics.Bitmap;

import com.frame.db.DBHelper;
import com.frame.preference.FrameDataConfig;
import com.lbsm.kdang.picture.LocalImageHelper;
import com.lbsm.kdang.utils.SdcardUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;

/**
 */

public class KDangApplication extends YCApplication {

    private static final String TAG = "KDangApplication";
    private static KDangApplication kDangApplication;
    private FrameDataConfig config;

    public DBHelper dbHelper;
    public LocalImageHelper localImageHelper;
    public File mPicFolder = null;
    public DisplayImageOptions options;

    @Override
    public void onCreate() {
        super.onCreate();
        kDangApplication = this;
        init();
    }

    public static KDangApplication getInstance() {
        return kDangApplication;
    }

    protected void init() {
        // TODO Auto-generated method stub
        //        List<Class> list = new ArrayList<Class>();
        //        list.add(SimpleUser.class);
        //        list.add(BarTop.class);
        //        list.add(PageDb.class);
        //        try {
        //            dbHelper = new DBHelper(this,"between",1,list,MessageDb.class);
        //        } catch (DbException e) {
        //            e.printStackTrace();
        //        }

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
        //        mPicFolder = FileUtil.avatarFile(instance);
        mPicFolder = new File(SdcardUtil.getRootPath() + "/kdang/cache");
        if (!mPicFolder.exists())
            mPicFolder.mkdirs();
        localImage();
        //        SDKInitializer.initialize(this);
    }

    public DisplayImageOptions imageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

    public void localImage() {
        if (localImageHelper == null)
            localImageHelper = new LocalImageHelper(this);
        localImageHelper.startLocalImage();
    }


    public FrameDataConfig getConfig() {
        return config == null ? config = new FrameDataConfig(this) : config;
    }
}
