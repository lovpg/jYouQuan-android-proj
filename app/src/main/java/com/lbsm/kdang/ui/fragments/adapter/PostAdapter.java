package com.lbsm.kdang.ui.fragments.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.frame.view.pullview.PullListView;
import com.lbsm.kdang.entity.Post;
import com.lbsm.kdang.entity.vo.BaseVo;
import com.lbsm.kdang.net.request.base.PageLoader;
import com.lbsm.kdang.ui.fragments.item.PostItem;

import java.util.List;

/**
 * date: 2016/11/1.
 */

public class PostAdapter extends PageAdapter<Post,PageLoader<? extends BaseVo<Post>>,PostAdapter.Holder> {

    private final String TAG="PostAdapter";

    public PostAdapter(Context context, PullListView plv,
                       PageLoader<? extends BaseVo<Post>> pageLoader,
                       boolean showHeaderView) {
        super(context, plv, pageLoader);
        refreshPage(showHeaderView);
    }

    public PostAdapter(Context context, PullListView plv, PageLoader<? extends BaseVo<Post>> postPageLoader ){
        super(context,plv,postPageLoader);
        refreshPage();
    }

    @Override
    public View getRawView(Context context, ViewGroup parent) {
        return new PostItem(context);
    }

    @Override
    public Holder initHolder(View view) {
        Holder holder=new Holder();
        holder.postItem=(PostItem) view;
        return holder;
    }

    @Override
    public void initView(Holder holder, int position, View convertView,
                         ViewGroup viewGroup, Post post) {
        Log.d(TAG,"post : "+post.toString());
        holder.postItem.setContent(post);
    }

    static class Holder{
        PostItem postItem = null;
    }

    public void addAll(List<Post> posts){
        dataList.addAll(posts);
        this.notifyDataSetChanged();
    }
}
