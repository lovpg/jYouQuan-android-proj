package com.lbsm.kdang.picture.activity.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.activity.BaseFragmentActivity;
import com.jaeger.library.StatusBarUtil;
import com.lbsm.kdang.R;


/**
 * Created by Administrator on 2015/11/30.
 */
public abstract  class LargeActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener {

    public final static int LARGE = 0x1001;
    public final static int LARGE_PREVIEW = 0x1002;

    protected ImageView imageBack;
    protected TextView imageCount;
    protected Button imageOk;
    protected ImageView pictureDelete;
    protected ViewPager pager;
    protected CheckBox checkBox;
    protected ImageView imageSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_big);
        StatusBarUtil.setColor(this, getColor(R.color.red_200), 50);
        init();
    }

    public void init(){
        imageBack = (ImageView) findViewById(R.id.image_back);
        imageCount = (TextView) findViewById(R.id.image_count);
        imageOk = (Button) findViewById(R.id.image_ok);
        pictureDelete = (ImageView) findViewById(R.id.picture_delete);
        pager = (ViewPager) findViewById(R.id.pager);
        checkBox = (CheckBox) findViewById(R.id.image_check);
        imageSave = (ImageView) findViewById(R.id.image_save);
        pager.setOnPageChangeListener(this);
        initView();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        LargeCount(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void imgaeCount(int position,int size){
        if(size > 0){
            imageCount.setText(position+1 + "/" + size);
        }
    }


    protected abstract void initView();
    protected abstract void LargeCount(int position);

}
