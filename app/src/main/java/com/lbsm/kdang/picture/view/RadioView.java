package com.lbsm.kdang.picture.view;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.frame.FrameConstant;
import com.frame.activity.BaseFragmentActivity;
import com.frame.utils.FileNameUtil;
import com.frame.utils.FileUtil;
import com.lbsm.kdang.picture.IntentUtil;
import com.lbsm.kdang.picture.adapter.RadioAdapter;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.util.ImageCompress;

/**
 * Created by Administrator on 2015/12/2.
 */
public class RadioView extends ThumbnailView implements AdapterView.OnItemClickListener{

    public File mCutFile;
    private String path;
    public RadioView(Context context) {
        super(context);
        init();
    }

    public RadioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void init() {
        multipleAdapter = new RadioAdapter(getContext());
        super.init();
        gridview.setOnItemClickListener(this);
        path = FileUtil.avatarFile(getContext()).getAbsolutePath();

    }

    @Override
    public void pitureOnClickOk() {
        if(camereLocale != null){
            mCutFile =  ImageCompress.cutPhoto(Uri.fromFile(new File(camereLocale.getThumbnaiPath())), path, getContext());
        }

    }

    @Override
    public void pitureOnClickBack() {
        if(pictureCamera.getVisibility() == View.GONE){
            IntentUtil.finishBottom(getContext());
        }else{
            pictureOk.setVisibility(View.GONE);
            pictureCamera.startAnimation(outAction);
            pictureCamera.setVisibility(View.GONE);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LocalFile localFile = multipleAdapter.getLocalFiles().get(position);
        if ("camera".equals(localFile.getThumbnailUri())) {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            ((BaseFragmentActivity) getContext()).startActivityForResult(getImageByCamera, FrameConstant.IMAGE_CAPTURE);
        } else {
            mCutFile =  ImageCompress.cutPhoto(Uri.fromFile(new File(localFile.getThumbnaiPath())), path,getContext());
        }
    }

    public File getCutPath(){
       File file = new File(FileNameUtil.createImageFilename(path));
       if(!file.exists()){
           try {
               file.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        return file;
    }
}
