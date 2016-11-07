package com.lbsm.kdang.picture.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lbsm.kdang.R;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.util.PictureShow;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2015/11/24.
 */
@SuppressLint("NewApi")
public class ImageBigItem extends LinearLayout{

    private ImageView dragImage;
    private ProgressBar progressBar;

    public ImageBigItem(Context context) {
        super(context);
        init();
    }

    public ImageBigItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

     public ImageBigItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

     public void init(){
         LayoutInflater.from(getContext()).inflate(R.layout.item_image_big,this);
         dragImage = (ImageView) findViewById(R.id.drag_image);
         progressBar = (ProgressBar) findViewById(R.id.progressbar);
     }

    public void setContent(LocalFile localFile){
        setImageUrl(localFile.getThumbnailUri());
    }


    public void setImageUrl(String url) {
        progressBar.setVisibility(View.GONE);
        if (url.startsWith("http") || url.startsWith("https")) {
            try {

                ImageLoader.getInstance().loadImage(url, KDangApplication.getInstance().options, new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String arg0, View arg1) {
                        // TODO Auto-generated method stub
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                        // TODO Auto-generated method stub
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                        // TODO Auto-generated method stub
                        progressBar.setVisibility(View.GONE);
                        dragImage.setImageBitmap(arg2);

                    }

                    @Override
                    public void onLoadingCancelled(String arg0, View arg1) {
                        // TODO Auto-generated method stub

                    }
                });
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else if(url.startsWith("content")){
            PictureShow.displayImage(dragImage,url);
        }else{
            url = PictureShow.getFilePath(url);
            PictureShow.displayImage(dragImage,url);
        }
        dragImage.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }
}
