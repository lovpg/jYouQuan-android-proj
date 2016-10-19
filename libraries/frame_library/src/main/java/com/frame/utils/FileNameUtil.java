/*
 * Copyright (C) 2014 The Android Open Source Project.
 *
 *        yinglovezhuzhu@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frame.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.view.Surface;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Use:
 * Created by yinglovezhuzhu@gmail.com on 2014-07-24.
 */
public class FileNameUtil {

	private FileNameUtil() {}


    public static int getDisplayRotation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        switch (rotation) {
            case Surface.ROTATION_0: return 0;
            case Surface.ROTATION_90: return 90;
            case Surface.ROTATION_180: return 180;
            case Surface.ROTATION_270: return 270;
        }
        return 0;
    }
    /**
     * 存储是否可用
     * @return
     */
    public static boolean hasStorage() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 根据文件夹名称生成一个应用数据目录
     * @param folderName
     * @return
     */
    public static String getApplicationFolder(String folderName) {
        if(StringUtil.isEmpty(folderName)) {
            return null;
        }
        if(hasStorage()) {
            File storage = Environment.getExternalStorageDirectory();
            File file =  new File(storage, folderName);
            if(!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
        return null;
    }

    /**
     * 根据系统时间产生一个在指定目录下的图片文件名
     * @param folder
     * @return
     */
    public static String createImageFilename(String folder) {
        return createFilename(folder, FrameConfig.IMAGE_PREFIX, FrameConfig.IMAGE_SUFFIX);
    }

    /**
     * 根据系统时间产生一个在指定目录下的视频文件名
     * @param folder
     * @return
     */
    public static String createVideoFilename(String folder) {
        return createFilename(folder, FrameConfig.VIDEO_PREFIX, FrameConfig.VIDEO_SUFFIX);
    }

    public static String  createVoiceFilename(String folder){
       return createFilename(folder,"voice_",FrameConfig.VOICE_SUFFIX);
    }

    /**
     * 根据系统时间产生一个临时视频目录，用来存放一个操作中的视频
     * @param parent
     * @return
     */
    public static String createVideosSessionFolder(String parent) {
        String folder = createFilename(parent, FrameConfig.VIDEO_PREFIX, FrameConfig.VIDEO_SESSION_FOLDER_SUFFIX);
        int index = 0;
        File file = new File(folder + "_" + index);
        while(file.exists()) {
            index++;
            file = new File(folder + "_" + index);
        }
        if(file.mkdirs()) {
            return file.getPath();
        }
        return folder;
    }

    /**
     * 根据系统时间、前缀、后缀产生一个文件名
     * @param folder
     * @param prefix
     * @param suffix
     * @return
     */
    private static String createFilename(String folder, String prefix, String suffix) {
        File file = new File(folder);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
//        String filename = prefix + System.currentTimeMillis() + suffix;
        SimpleDateFormat dateFormat = new SimpleDateFormat(FrameConfig.DATE_FORMAT_MILLISECOND);
        String filename = prefix + dateFormat.format(new Date(System.currentTimeMillis())) + suffix;
        return new File(folder, filename).getAbsolutePath();
    }

    /**
     * Check if external storage is built-in or removable.
     *
     * @return True if external storage is removable (like an SD card), false
     * otherwise.
     */
    @SuppressLint("NewApi")
	public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    /**
     * Get the external app cache directory.
     *
     * @param context The context to use
     * @return The external cache dir
     */
    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            File cacheDir = context.getExternalCacheDir();
            if(null != cacheDir) {
                return cacheDir;
            }
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory(), cacheDir);
    }

    /**
     * Get the external app data directory
     * @param context
     * @return
     */
    public static File getExternalDataDir(Context context) {
        if(hasExternalCacheDir()) {
            File cacheDir = context.getExternalCacheDir();
            if(null != cacheDir) {
                return cacheDir.getParentFile();
            }
        }
        final String dateDir = "Android/data" + context.getPackageName();
        return new File(Environment.getExternalStorageDirectory(), dateDir);
    }

    /**
     * Get the external app files directory
     * @param context
     * @return
     */
    public static File getExternalFilesDir(Context context) {
        if (hasExternalCacheDir()) {
            File filesDir = context.getExternalFilesDir(null);
            if(filesDir != null) {
                return filesDir;
            }
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "Android/data/" + context.getPackageName() + "/files";
        return new File(Environment.getExternalStorageDirectory(), cacheDir);
    }

    /**
     * 草稿箱文件保存
     * @param context
     * @return
     */
    public static File getExternalDraftDir(Context context) {
        if (hasExternalCacheDir()) {
            File filesDir = context.getExternalFilesDir(null);
            if(filesDir != null) {
                return filesDir;
            }
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "Android/data/" + context.getPackageName() + "/draft/";
        return new File(Environment.getExternalStorageDirectory(), cacheDir);
    }
    
    /**
     * Get the external app local cache directory
     * @param context
     * @return
     */
    public static File getExternalLocalCacheDir(Context context) {
        File dataDir = getExternalDataDir(context);
        if(null != dataDir) {
            return new File(dataDir, "local_cache");
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "Android/data/" + context.getPackageName() + "/local_cache/";
        return new File(Environment.getExternalStorageDirectory(), cacheDir);
    }

    /**
     * Check if OS version has built-in external cache dir method.
     *
     * @return
     */
    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }


    public static byte[] rotateYUV420Degree90(byte[] data, int imageWidth, int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        // Rotate the Y luma
        int i = 0;
        for (int x = 0; x < imageWidth; x++) {
            for (int y = imageHeight - 1; y >= 0; y--) {
                yuv[i] = data[y * imageWidth + x];
                i++;
            }

        }
        // Rotate the U and V color components
        i = imageWidth * imageHeight * 3 / 2 - 1;
        for (int x = imageWidth - 1; x > 0; x = x - 2) {
            for (int y = 0; y < imageHeight / 2; y++) {
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + x];
                i--;
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + (x - 1)];
                i--;
            }
        }
        return yuv;
    }

    public static byte[] rotateYUV420Degree180(byte[] data, int imageWidth, int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        int i = 0;
        int count = 0;

        for (i = imageWidth * imageHeight - 1; i >= 0; i--) {
            yuv[count] = data[i];
            count++;
        }

        i = imageWidth * imageHeight * 3 / 2 - 1;
        for (i = imageWidth * imageHeight * 3 / 2 - 1; i >= imageWidth
                * imageHeight; i -= 2) {
            yuv[count++] = data[i - 1];
            yuv[count++] = data[i];
        }
        return yuv;
    }

    public static byte[] rotateYUV420Degree270(byte[] data, int imageWidth, int imageHeight) {

        /*byte [] yuv = new byte[imageWidth*imageHeight*3/2];
        int nWidth = 0, nHeight = 0;
        int wh = 0;
        int uvHeight = 0;
        if(imageWidth != nWidth || imageHeight != nHeight)
        {
            nWidth = imageWidth;
            nHeight = imageHeight;
            wh = imageWidth * imageHeight;
            uvHeight = imageHeight >> 1;//uvHeight = height / 2
        }

        //旋转Y
        int k = 0;
        for(int i = 0; i < imageWidth; i++) {
            int nPos = 0;
            for(int j = 0; j < imageHeight; j++) {
                yuv[k] = data[nPos + i];
                k++;
                nPos += imageWidth;
            }
        }

        for(int i = 0; i < imageWidth; i+=2){
            int nPos = wh;
            for(int j = 0; j < uvHeight; j++) {
                yuv[k] = data[nPos + i];
                yuv[k + 1] = data[nPos + i + 1];
                k += 2;
                nPos += imageWidth;
            }
        }
        return rotateYUV420Degree180(yuv, imageWidth, imageHeight);*/

        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        // Rotate the Y luma
        int i = 0;
        for (int x = imageWidth - 1; x >= 0; x--) {
            for (int y = 0; y < imageHeight; y++) {
                yuv[i] = data[y * imageWidth + x];
                i++;
            }

        }
        // Rotate the U and V color components
        i = imageWidth * imageHeight;
        for (int x = imageWidth - 1; x > 0; x = x - 2) {
            for (int y = 0; y < imageHeight / 2; y++) {
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + (x - 1)];
                i++;
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + x];
                i++;
            }
        }
        return yuv;
    }
}
