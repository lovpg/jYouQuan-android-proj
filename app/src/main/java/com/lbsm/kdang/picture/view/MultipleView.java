package com.lbsm.kdang.picture.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.frame.FrameConstant;
import com.frame.activity.BaseFragmentActivity;
import com.lbsm.kdang.base.BaseActivity;
import com.lbsm.kdang.picture.IntentUtil;
import com.lbsm.kdang.picture.activity.LargeMultipleActivity;
import com.lbsm.kdang.picture.activity.base.LargeActivity;
import com.lbsm.kdang.picture.adapter.MultipleAdapter;
import com.lbsm.kdang.picture.entity.LocalFile;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2015/12/1.
 */
public class MultipleView extends ThumbnailView implements MultipleItem.OnMultipleClickListent,OnItemClickListener {

    public MultipleView(Context context) {
        super(context);
        init();
    }

    public MultipleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultipleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        multipleAdapter = new MultipleAdapter(getContext());
        super.init();
        multipleAdapter.setonMultipleClickListent(this);
        gridview.setOnItemClickListener(this);
    }

    @Override
    public void pitureOnClickOk() {

        Collections.sort(localFiles, new Comparator<LocalFile>() {
            @Override
            public int compare(LocalFile lhs, LocalFile rhs) {
                return lhs.getTime() > rhs.getTime() ? 0 : -1;
            }
        });
        BaseActivity baseActivity = ((BaseActivity) getContext());
        Intent intent = baseActivity.getIntent();
        Bundle bundle = new Bundle();
        if(pictureCamera.getVisibility() == View.GONE){
            bundle.putSerializable("localFiles", (Serializable) localFiles);
            intent.putExtra("isCamera", false);
            intent.putExtras(bundle);
        }else{
            bundle.putSerializable("localFile",camereLocale);
            intent.putExtra("isCamera", true);
            intent.putExtras(bundle);
        }
        baseActivity.setResult(baseActivity.RESULT_OK,intent);
        IntentUtil.finishBottom(getContext());
    }

    @Override
    public void pitureOnClickBack() {
        if(pictureCamera.getVisibility() == View.GONE){
            IntentUtil.finishBottom(getContext());
        }else{
            pictureCamera.startAnimation(outAction);
            pictureCamera.setVisibility(View.GONE);
        }
    }

    @Override
    public void OnCheckClick(LocalFile localFile) {

        selectPicture(localFile);
        multipleAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LocalFile localFile = multipleAdapter.getLocalFiles().get(position);
        if("camera".equals(localFile.getThumbnailUri())){
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            ((BaseFragmentActivity)getContext()).startActivityForResult(getImageByCamera, FrameConstant.IMAGE_CAPTURE);
        }else{
            LargeMultipleActivity.StartMultipActivity(getContext(), multipleAdapter.getLocalFiles(), position, number, LargeActivity.LARGE);
        }
    }
}
