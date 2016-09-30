package com.lbsm.kdang.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.lbsm.kdang.KdangApplication;
import com.lbsm.kdang.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageUtil {

    public static void loadImage(final ImageView imageView, String path) {
        // TODO Auto-generated method stub
    	try {
            ImageLoader.getInstance().loadImage(path, null, KdangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        imageView.setImageResource(R.mipmap.img_avatar_stub);
                    else
                        imageView.setImageBitmap(arg2);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }


    }



	
}
