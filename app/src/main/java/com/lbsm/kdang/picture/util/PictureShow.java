package com.lbsm.kdang.picture.util;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.frame.FrameApplication;
import com.frame.FrameConstant;
import com.lbsm.kdang.KdangApplication;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2015/11/27.
 */
public class PictureShow {

    public static void displayImage(final ImageView imageView,final String uri){
        ImageLoader.getInstance().displayImage(uri, new ImageViewAware(imageView,false), KdangApplication.getInstance().options);
    }

    public static LocalFile saveCapture(Context context,String  str){
        LocalFile localFile = null;

        Cursor cursor = context.getContentResolver().query(Uri.parse(str), FrameConstant.STORE_IMAGES, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            localFile = new LocalFile();
            int id = cursor.getInt(0);
            String path = cursor.getString(cursor.getColumnIndexOrThrow(FrameConstant.STORE_IMAGES[1]));

            //小图URI
            String thumbUri = getThumbnail(context, id, path);

            //       //获取大图URI
            String uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(Integer.toString(id)).build().toString();
            int degree = cursor.getInt(2);
            if (degree != 0) degree = degree + 180;
            localFile.setIsSelect(false);
            localFile.setOriginalUri(uri);
            localFile.setThumbnailUri(thumbUri);
            localFile.setThumbnaiPath(path);
            localFile.setTime(0);
            localFile.setOrientation(360 - degree);
        }
        cursor.close();
        return localFile;
    }

    private static String getThumbnail(Context context,int id, String path) {
        //获取大图的缩略图
        String uri = null;
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, FrameConstant.THUMBNAIL_STORE_IMAGE,
                MediaStore.Images.Thumbnails.IMAGE_ID + " = ?", new String[]{id + ""}, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int thumId = cursor.getInt(0);
            uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI.buildUpon().appendPath(Integer.toString(thumId)).build().toString();
            cursor.close();
            return uri;
        }
        cursor.close();
        return uri;
    }
    



    public void displayImage(final String uri, final ImageView imageView){
        ImageLoader.getInstance().displayImage(uri, imageView, KdangApplication.getInstance().options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                if(s.equals(uri)){
                    imageView.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }

    public static String getFilePath(String path){
        return "file://"+path;
    }
    
    public static Bitmap getSmallBitmap(String filePath) {
		Bitmap bitmap = null;
		int width = 0;
		int height = 0;
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

    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap,0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap == null ? bitmap : resizedBitmap;
    }

    
}
