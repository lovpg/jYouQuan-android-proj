package com.lbsm.kdang.picture.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lbsm.kdang.picture.view.RadioItem;

/**
 * Created by Administrator on 2015/12/2.
 */
public class RadioAdapter extends PictureAdapter {

    private Context mContext;

    public RadioAdapter(Context context){
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
        RadioItem radioItem = null;
        if(convertView == null){
            radioItem = new RadioItem(mContext);
            convertView = radioItem;
        }else radioItem = (RadioItem) convertView;
        radioItem.setImageUrl(localFiles.get(position).getThumbnailUri());
        return convertView;
    }
}
