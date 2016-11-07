package com.lbsm.kdang.ui.fragments.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frame.view.pullview.PullListView;
import com.lbsm.kdang.R;
import com.lbsm.kdang.base.BaseFragment;
import com.lbsm.kdang.entity.Post;
import com.lbsm.kdang.net.request.PostFollowReq;
import com.lbsm.kdang.ui.fragments.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * date: 2016/10/21.
 */

public class PostFollowFragm extends BaseFragment{

    private static final String TAG = "PostFollowFragm";
    @Bind(R.id.plv_post_essence)
    PullListView mPullListView;

    private PostAdapter mPostAdapter;

    private Context mContext;

    private List<Post> mPosts;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mPosts = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragm_post_essence, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if(mPostAdapter == null){
            if (mPosts != null && mPosts.size() > 0) {
                mPostAdapter = new PostAdapter(mContext, mPullListView, new PostFollowReq(), false);
                mPostAdapter.addAll(mPosts);
            } else
                mPostAdapter = new PostAdapter(getActivity(), mPullListView, new PostFollowReq(), true);
        }
    }

    public void setRefresh(Post post) {
        if (mPostAdapter == null && mPostAdapter.getCount() <= 0) return;
        for (int i = 0; i < mPostAdapter.getDataList().size(); i++) {
            if (mPostAdapter.getDataList().get(i).getShareId().equals(post.getShareId())) {
                mPostAdapter.getDataList().set(i, post);
                break;
            }
        }
        mPostAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
