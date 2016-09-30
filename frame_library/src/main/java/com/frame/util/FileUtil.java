package com.frame.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;

public class FileUtil {

	public static File avatarFile(Context context) {
        // TODO Auto-generated method stub
        File mPicFolder = null;
        try {
            mPicFolder = new File(FileNameUtil.getExternalFilesDir(context), FrameConfig.CACHE_AVATAR_DIR);
            if (!mPicFolder.exists()) {
                mPicFolder.mkdirs();
            }
            return mPicFolder;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mPicFolder;
    }


    public static long getFileSizes(File f) throws Exception{
        long s=0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
			fis.close();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
       
        return s;
    }
    public static void saveBitmap(File file,Bitmap bitmap){
		try{
			FileOutputStream fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 85, fos);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
