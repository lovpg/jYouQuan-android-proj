package com.lbsm.kdang.picture.view;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lbsm.kdang.KdangApplication;
import com.lbsm.kdang.R;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2015/11/20.
 */
@SuppressLint("NewApi")
public class AlumbItem extends LinearLayout{

    private ImageView alumbImage;
    private TextView alumbName;
    private CheckBox alumbCheck;
    private View viewAlumb;
    public Map<String,List<LocalFile>> foldres = null;
    public AlumbItem(Context context) {
        super(context);
        init();
    }

    public AlumbItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

     public AlumbItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.item_alumb,this);
        alumbImage = (ImageView) findViewById(R.id.alumb_image);
        alumbName = (TextView) findViewById(R.id.alumb_name);
        viewAlumb = findViewById(R.id.view_alumb);

        alumbCheck = (CheckBox) findViewById(R.id.alumb_check);
        foldres = KdangApplication.getInstance().localImageHelper.foldres;
    }

    public void setContent(String name,String folder){
        List<LocalFile> localFiles = foldres.get(name);
        if(localFiles != null && localFiles.size() > 0){
            String url = localFiles.get(0).getThumbnailUri();
            ImageLoader.getInstance().displayImage(url,alumbImage, KdangApplication.getInstance().options,new ImageLoadingListener(){

                @Override
                public void onLoadingStarted(String s, View view) {

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    if (TextUtils.isEmpty(s))
                        alumbImage.setImageResource(R.mipmap.img_avatar_stub);
                    else
                        alumbImage.setImageBitmap(bitmap);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
        }

        alumbName.setText(name + " (" + localFiles.size() + ") ");
        viewAlumb.setTag(name);
        if(name.equals(folder))alumbCheck.setChecked(true);
        else alumbCheck.setChecked(false);
    }
}
