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
public class MultipleItem extends ThumbnailItem implements View.OnClickListener{

    public OnMultipleClickListent onMultipleClickListent;

    public MultipleItem(Context context) {
        super(context);
    }

    public MultipleItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultipleItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setContent(LocalFile localFile){
        setImageUrl(localFile.getThumbnailUri());
        setIsCheck(localFile.isSelect());
        checkBox.setTag(localFile);
        checkBox.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        LocalFile localFile = (LocalFile) v.getTag();
        if(localFile.isSelect()){
            localFile.setTime(0);
            localFile.setIsSelect(false);
        }else{
            localFile.setTime(System.currentTimeMillis());
            localFile.setIsSelect(true);
        }
        onMultipleClickListent.OnCheckClick(localFile);
    }

    public void setImageUrl(String url){
        localImage.setImageResource(R.mipmap.img_avatar_stub);

        if("camera".equals(url)){
            checkBox.setVisibility(View.GONE);
            localImage.setImageResource(R.mipmap.image_camera);
        }else{
            checkBox.setVisibility(View.VISIBLE);
            PictureShow.displayImage(localImage, url);
        }
    }

    public void setonMultipleClickListent(OnMultipleClickListent onMultipleClickListent){
        this.onMultipleClickListent = onMultipleClickListent;
    }


    public interface OnMultipleClickListent{
        void OnCheckClick(LocalFile localFile);
    }
}
