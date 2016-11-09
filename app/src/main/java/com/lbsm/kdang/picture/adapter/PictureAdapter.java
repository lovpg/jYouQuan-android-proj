package com.lbsm.kdang.picture.adapter;

import java.util.ArrayList;
import java.util.List;

import android.widget.BaseAdapter;

import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.view.MultipleItem;


/**
 * Created by Administrator on 2015/12/2.
 */
public abstract class PictureAdapter extends BaseAdapter {

    List<LocalFile> localFiles = new ArrayList<LocalFile>();
    protected MultipleItem.OnMultipleClickListent onMultipleClickListent;

    public void addAll(List<LocalFile> list){
        localFiles.clear();
        localFiles.addAll(list);
        notifyDataSetChanged();
    }

    public List<LocalFile> getLocalFiles() {
        return localFiles;
    }

    public void setonMultipleClickListent(MultipleItem.OnMultipleClickListent onMultipleClickListent){
        this.onMultipleClickListent = onMultipleClickListent;
    }

}
