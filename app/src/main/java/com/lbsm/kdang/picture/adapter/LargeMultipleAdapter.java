package com.lbsm.kdang.picture.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.view.ImageBigItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class LargeMultipleAdapter extends PagerAdapter {

    public List<LocalFile> localFiles = new ArrayList<LocalFile>();
    public Context mContext;
    public LargeMultipleAdapter(Context context){
        this.mContext = context;

    }

    public void addAll(List<LocalFile> list){
        localFiles.clear();
        localFiles.addAll(list);
        notifyDataSetChanged();
    }

    public List<LocalFile> getLocalFiles() {
        return localFiles;
    }

    @Override
    public int getCount() {
        return localFiles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageBigItem imageBigItem = new ImageBigItem(mContext);
        imageBigItem.setContent(localFiles.get(position));
        container.addView(imageBigItem);
        return imageBigItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }
}
