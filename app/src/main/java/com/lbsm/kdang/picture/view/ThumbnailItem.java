package com.lbsm.kdang.picture.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.frame.util.DensityUtil;
import com.lbsm.kdang.R;

/**
 * Created by Administrator on 2015/11/27.
 */
@SuppressLint("NewApi") 
public class ThumbnailItem extends LinearLayout{


    protected ImageView localImage;
    protected CheckBox checkBox;
    protected View checkView;

    public ThumbnailItem(Context context) {
        super(context);
        init();
    }

    public ThumbnailItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

   public ThumbnailItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.item_local,this);
        localImage = (ImageView) findViewById(R.id.local_image);
        checkView = findViewById(R.id.check_view);
        checkBox = (CheckBox) findViewById(R.id.local_check);

        int width = DensityUtil.getWidth(getContext()) / 3;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width,width);
        localImage.setLayoutParams(layoutParams);
    }




    public void setIsCheck(boolean isCheck){
        checkBox.setChecked(isCheck);
        if(isCheck)checkView.setBackgroundResource(R.color.c424344);
        else checkView.setBackgroundResource(android.R.color.transparent);

    }



}
