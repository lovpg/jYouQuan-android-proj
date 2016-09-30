package com.lbsm.kdang.picture.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.bither.util.NativeUtil;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.frame.FrameConstant;
import com.frame.activity.BaseFragmentActivity;
import com.frame.util.FileNameUtil;
import com.frame.util.FileUtil;
import com.frame.util.FrameConfig;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.monitor.OnCompressListener;

/**
 * Created by Administrator on 2015/11/24.
 */
public class ImageCompress {

    public static void compressBitemp(final List<LocalFile> localFiles, final String savePath, final OnCompressListener onCompressListener){

        new AsyncTask<Void, Integer, Boolean>(){

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    for (LocalFile localFile : localFiles){
                        String name[] = localFile.getThumbnaiPath().split("/");
                        String imageName = name[name.length - 1];
                        File file = new File(savePath,imageName);
                        if(file.exists()){
                            localFile.setCompressUri(file.getAbsolutePath());
                        }else{
                            File compress = saveBitmap(localFile.getThumbnaiPath(),file.getAbsolutePath());
                            localFile.setCompressUri(compress.getAbsolutePath());
                        }
                    }
                }catch (Exception e){
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                onCompressListener.OnCompress(aBoolean.booleanValue());
                super.onPostExecute(aBoolean);
            }
        }.execute();
    }


    public static File saveBitmap(String sourcePath, String savePath) {
        // TODO Auto-generated method stu
        File saveFile = null;
        try {
            Bitmap bitmap = PictureShow.getSmallBitmap(sourcePath);
            int quality = getCompressionParameter(sourcePath);
            if(quality == 0 || bitmap == null) saveFile =  new File(sourcePath);
            else {
                saveFile = new File(savePath);
                saveFile.createNewFile();
                NativeUtil.compressBitmap(bitmap, quality, saveFile.getAbsolutePath(), true, 2);
            }

            return saveFile;

        } catch (OutOfMemoryError e) {
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return new File(sourcePath);
    }



    public static int  getCompressionParameter(String path) throws Exception {
        int quality = 0;
        long kb = FileUtil.getFileSizes(new File(path)) / 1024 ;
        if(kb <= 200) quality = 0;
        else if(kb <= 500) quality = 90;
        else if(kb <= 1024) quality = 80;
        else if(kb <= 1024 * 2) quality = 70;
        else if(kb <= 1024 * 3) quality = 60;
        else quality = 40;
        return quality;
    }

    public static Bitmap getSmallBitmap(String filePath) {
        Bitmap bitmap = null;
        try{
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            bitmap = BitmapFactory.decodeFile(filePath,opt);
            bitmap = rotaingImageView(readPictureDegree(filePath), bitmap);
        }catch (OutOfMemoryError e){
            return null;
        }catch (Exception e){
            return null;
        }
        return bitmap;
    }


    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    /*
     * ��תͼƬ
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap,0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap == null ? bitmap : resizedBitmap;
    }



    /**
     *
     * @param uri
     * @param path 文件地址
     * @param context
     * @return
     */
    public static File cutPhoto(Uri uri,String path,Context context) {
        // TODO Auto-generated method stub
        File mCutFile = new File(FileNameUtil.createImageFilename(path));
        cutPhoto(uri, FrameConfig.AVATAR_SIZE, mCutFile, context);
        return mCutFile;
    }

    /**
     * 调用系统的图片编辑工具剪切图片
     * @param uri source data
     * @param size
     */
    public static void cutPhoto(Uri uri, int size,File cutFIle,Context context) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        //设置是否返回data数据
        intent.putExtra("return-data", true);
        //设置输出文件
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cutFIle));
        ((BaseFragmentActivity)context).startActivityForResult(intent, FrameConstant.IMAGE_CUT);
    }


}
