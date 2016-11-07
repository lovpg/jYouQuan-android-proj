package com.lbsm.kdang.picture.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lbsm.kdang.picture.view.MultipleItem;

/**
 * Created by Administrator on 2015/11/27.
 */
public class MultipleAdapter extends PictureAdapter{

    private Context mContext;

    public MultipleAdapter(Context context){
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return localFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return localFiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MultipleItem multipleItem = null;
        if(convertView == null){
            multipleItem = new MultipleItem(mContext);
            convertView = multipleItem;
        }else multipleItem = (MultipleItem) convertView;
        multipleItem.setContent(localFiles.get(position));
        multipleItem.setonMultipleClickListent(onMultipleClickListent);
        return convertView;
    }
}
