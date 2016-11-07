package com.lbsm.kdang.picture.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lbsm.kdang.picture.view.AlumbItem;

/**
 * Created by Administrator on 2015/11/20.
 */
public class AlumbAdapter extends BaseAdapter{

    List<String> datas = new ArrayList<String>();
    private Context mContent;
    private String folder;

    public AlumbAdapter(Context content){
        this.mContent = content;
    }

    public void addAll(List<String> list){
        datas.clear();
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public List<String> getDatas() {
        return datas;
    }

    public void selectAlumb(String folder){
        this.folder = folder;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlumbItem alumbItem = null;
        if(convertView == null){
            alumbItem = new AlumbItem(mContent);
            convertView = alumbItem;
        }else{
            alumbItem = (AlumbItem) convertView;
        }
        alumbItem.setContent(datas.get(position),folder);
        return convertView;
    }
}
