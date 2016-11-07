package com.lbsm.kdang.picture.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.lbsm.kdang.R;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.util.PictureShow;

/**
 * Created by Administrator on 2015/11/27.
 */
public class RadioItem extends ThumbnailItem{

    public RadioItem(Context context) {
        super(context);
    }

    public RadioItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setContent(LocalFile localFile){
        setImageUrl(localFile.getThumbnailUri());
        checkBox.setVisibility(View.GONE);
        checkView.setVisibility(View.GONE);
    }

    public void setImageUrl(String url){
        localImage.setImageResource(R.mipmap.img_avatar_stub);

        if("camera".equals(url)){
            localImage.setImageResource(R.mipmap.image_camera);
        }else{
            PictureShow.displayImage(localImage, url);
        }
    }
}
