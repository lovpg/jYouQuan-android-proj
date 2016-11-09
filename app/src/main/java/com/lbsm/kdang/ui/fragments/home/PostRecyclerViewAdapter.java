package com.lbsm.kdang.ui.fragments.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.lbsm.kdang.R;
import com.lbsm.kdang.entity.Post;
import com.lbsm.kdang.widget.CircleImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * date: 2016/10/24.
 */

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.PostRecyclerViewHolder> {

    private static final String TAG = "PostRecyclerViewAdapter";

    private Context mContext;
    private List<Post> mPosts;

    public PostRecyclerViewAdapter(Context context, List<Post> posts) {
        mContext = context;
        mPosts = posts;
    }

    @Override
    public PostRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_post_essence, parent, false);
        PostRecyclerViewHolder viewHolder = new PostRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostRecyclerViewHolder holder, int position) {
        if (mPosts != null) {
            Log.d(TAG, "posts size: " + mPosts.size());
        }
        Post post = mPosts.get(position);
        holder.userNameTv.setText(post.getUser().getNickname().toString().trim());
        holder.tagsTv.setText(post.getTags().toString().trim());
        holder.contentTv.setText(post.getContent().toString().trim());
        holder.commentCountTv.setText("" + post.getCommentCount());
        holder.praiseCountTv.setText("" + post.getGoodCount());
        holder.seeCountTv.setText("" + post.getEnrollCount());
        holder.dataTimeTv.setText(post.getCposttime().toString().trim());
        holder.locationTv.setText(post.getAddress().toString().trim());

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


    public void setData(List<Post> posts) {
        mPosts.clear();
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    class PostRecyclerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.civ_avatar)
        CircleImageView avatarCiv;
        @Bind(R.id.tv_username)
        TextView userNameTv;
        @Bind(R.id.tv_tags)
        TextView tagsTv;
        @Bind(R.id.btn_follow)
        Button followBtn;
        @Bind(R.id.tv_data_time)
        TextView dataTimeTv;
        @Bind(R.id.tv_content)
        TextView contentTv;
        @Bind(R.id.ngimg_nine_imageview)
        NineGridImageView nineImageViewNiv;
        @Bind(R.id.tv_location)
        TextView locationTv;
        @Bind(R.id.img_praise)
        ImageView praiseIv;
        @Bind(R.id.img_comment)
        ImageView commentIv;
        @Bind(R.id.img_see)
        ImageView seeIv;
        @Bind(R.id.img_share)
        ImageView shareIv;
        @Bind(R.id.tv_praise_count)
        TextView praiseCountTv;
        @Bind(R.id.tv_comment_count)
        TextView commentCountTv;
        @Bind(R.id.tv_see_count)
        TextView seeCountTv;


        public PostRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
