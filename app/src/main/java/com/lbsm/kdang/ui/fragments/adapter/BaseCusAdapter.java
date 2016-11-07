package com.lbsm.kdang.ui.fragments.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 2016/11/1.
 */

public abstract class BaseCusAdapter <T,H> extends BaseAdapter {

    protected List<T> dataList = new ArrayList<T>();
    protected Context context;
    private OnDataCountListener listener;

    public abstract View getRawView(Context context,ViewGroup parent);
    public abstract H initHolder(View view);
    public abstract void initView(H holder, int position,
                                  View convertView, ViewGroup viewGroup, T t);

    public interface OnDataCountListener{
        void onCount(int count);
    }

    public BaseCusAdapter(Context context) {
        this(context, null);
    }

    public BaseCusAdapter(Context context, OnDataCountListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if(listener != null) listener.onCount(getCount());
    }

    @Override
    public int getCount() {
        return dataList == null ? 0: dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder;
        if(convertView == null){
            convertView = getRawView(context, parent);
            holder = initHolder(convertView);
            convertView.setTag(holder);
        } else holder = (H)convertView.getTag();

        initView(holder, position, convertView, parent, getItem(position));
        return convertView;
    }

    public void setOnCountListener(OnDataCountListener listener) {
        this.listener = listener;
    }

    protected void displayToast(String info){
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public void addData(List<T> list){
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(T t){
        dataList.add(t);
        notifyDataSetChanged();
    }

    public void remove(int position){
        dataList.remove(position);
        notifyDataSetChanged();
    }

    public void clear(){
        dataList.clear();
    }

    public List<T> getDataList() {
        return dataList;
    }
}
