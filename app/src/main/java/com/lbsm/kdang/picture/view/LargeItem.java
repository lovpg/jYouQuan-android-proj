package com.lbsm.kdang.picture.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.frame.util.StringUtils;
import com.lbsm.kdang.R;


/**
 * Created by Administrator on 2015/11/27.
 */
@SuppressLint("NewApi")
public class LargeItem extends LinearLayout{

    protected ImageView dragImage;
    protected ProgressBar progressBar;

    public LargeItem(Context context) {
        super(context);
        init();
    }

    public LargeItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

     public LargeItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.item_image_big,this);
        dragImage = (ImageView) findViewById(R.id.drag_image);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    public void setLargeUrl(String url){
        if(!StringUtils.isHttp(url)){

        }
    }
}
