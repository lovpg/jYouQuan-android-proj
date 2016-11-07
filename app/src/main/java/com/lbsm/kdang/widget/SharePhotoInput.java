package com.lbsm.kdang.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.frame.FrameConstant;
import com.frame.view.image.NoScrollGridView;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.picture.activity.LargeRadioActivity;
import com.lbsm.kdang.picture.activity.PictureMultipleActivity;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.monitor.OnCompressListener;
import com.lbsm.kdang.picture.util.ImageCompress;
import com.lbsm.kdang.widget.adapter.PhotoInputAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Share内容输入框.
 *
 * @author 阿海
 *
 */
public class SharePhotoInput extends NoScrollGridView implements OnItemClickListener,OnCompressListener {

    public static final int PHOTO_INPUT = 0x105;
    private List<String> imageList = new ArrayList<String>();

    private PhotoInputAdapter photoInputAdapter;

    public File mPicFile;
    public List<LocalFile> localFiles = new ArrayList<LocalFile>();
    public File mPicFolder = null;
    public boolean isCompress = false;

    public SharePhotoInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        photoInputAdapter = new PhotoInputAdapter(context);
        this.setAdapter(photoInputAdapter);

        this.setVerticalSpacing(10);
        this.setHorizontalSpacing(10);
        this.setNumColumns(4);
        this.setOnItemClickListener(this);

        this.setSelector(new ColorDrawable(Color.TRANSPARENT));

        imageList.add("add");
        photoInputAdapter.addAll(imageList);
    }


    public void addLocalFile(LocalFile localFile){
        localFiles.add(localFile);
        imageList.add(imageList.size() - 1, localFile.getThumbnailUri());
        if(localFiles.size() >= 9) imageList.remove("add");
        photoInputAdapter.addAll(imageList);
        isCompress = false;
        ImageCompress.compressBitemp(localFiles, KDangApplication.getInstance().mPicFolder.getAbsolutePath(), this);
    }

    public void cleanFiles(){
        localFiles.clear();
    }

    public void addAllLocalFile(List<LocalFile>list){
        imageList.clear();
        localFiles.addAll(list);
        for (LocalFile localFile : localFiles){
            imageList.add(localFile.getThumbnailUri());
        }
        if(localFiles.size() < 9)imageList.add("add");
        photoInputAdapter.addAll(imageList);
        isCompress = false;
        ImageCompress.compressBitemp(localFiles, KDangApplication.getInstance().mPicFolder.getAbsolutePath(), this);
    }


    public void reducePath(String path) {
        if(imageList.contains(path)){
            imageList.remove(path);
        }
    }

    public List<LocalFile> getLocalFiles() {
        return localFiles;
    }

    public List<String> getImageList() {
        return imageList;
    }

    @Override
    public void OnCompress(boolean isCompress) {
        this.isCompress = isCompress;
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
      
        if ("add".equals(imageList.get(arg2))) {
            int num = 9 - imageList.size()+1;
            PictureMultipleActivity.startPictureActivity(getContext(), num, FrameConstant.IMAGE_SELECT);

        }else{
            LargeRadioActivity.startLargeRadioActivity(getContext(), true, imageList, arg2, FrameConstant.IMAGE_DELETE);
        }
    }

  
    public boolean isCompress() {
        return isCompress;
    }
}
