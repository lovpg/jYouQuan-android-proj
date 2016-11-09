package com.lbsm.kdang.utils;

import android.os.Environment;

import java.io.File;

/**
 * date: 2016/10/19.
 */

public class SdcardUtil {

    /**
     * 获取扩展SD卡存储目录
     *
     * 如果有外接的SD卡，并且已挂载，则返回这个外置SD卡目录
     * 否则：返回内置SD卡目录
     *
     * @return
     */
    public static String getRootPath() {

        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File sdCardFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sdCardFile.getAbsolutePath();
        }
        return Environment.getRootDirectory().getAbsolutePath();
    }

}
