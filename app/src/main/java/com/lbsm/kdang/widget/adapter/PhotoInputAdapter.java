package com.lbsm.kdang.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lbsm.kdang.R;
import com.lbsm.kdang.picture.util.PictureShow;
import com.lbsm.kdang.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class PhotoInputAdapter extends BaseAdapter {

    private Context mContent;
    private List<String> imageList = new ArrayList<String>();
    public PhotoInputAdapter(Context context){
        this.mContent = context;

    }

    public void addAll(List<String> list){
        imageList.clear();
        imageList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContent).inflate(R.layout.griditem_photo_select, null,false);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.item_grid_icon);
        imageView.setTag(position);
        load(imageView, imageList.get(position));
        return convertView;
    }

    protected void load(ImageView imageView, String url) {
        if ("add".equals(url)) {
            imageView.setImageResource(R.mipmap.olla_share_add_n);
        }else{
            if (url.startsWith("http") || url.startsWith("https")) {
                ImageUtil.loadImage(imageView, url);
            }else if(url.startsWith("content")){
                PictureShow.displayImage(imageView, url);
            }else{
                url = PictureShow.getFilePath(url);
                PictureShow.displayImage(imageView,url);
            }
        }



    }

}
