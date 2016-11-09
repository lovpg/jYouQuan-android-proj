package com.lbsm.kdang.ui.fragments.item;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.frame.utils.ActivityContext;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.lbsm.kdang.R;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.entity.OtherPerson;
import com.lbsm.kdang.entity.Post;
import com.lbsm.kdang.entity.vo.BooleanVo;
import com.lbsm.kdang.entity.vo.OtherPersonVo;
import com.lbsm.kdang.net.request.FollowPersonReq;
import com.lbsm.kdang.net.request.IsFollowReq;
import com.lbsm.kdang.picture.activity.LargeRadioActivity;
import com.lbsm.kdang.ui.fragments.home.PostEssenceFragm;
import com.lbsm.kdang.ui.fragments.home.PostFollowFragm;
import com.lbsm.kdang.ui.fragments.home.PostNewFragm;
import com.lbsm.kdang.utils.DateTime;
import com.lbsm.kdang.utils.ImageUtil;

import java.util.Date;
import java.util.List;

/**
 * date: 2016/11/1.
 */

public class PostItem extends BaseItem implements View.OnClickListener, OnParseObserver2<Object>,OnFailSessionObserver2 {

    private final String TAG="PostItem";

    private Post mPost;
    private boolean mIsResult=true;

    private IsFollowReq mIsFollowReq;

    public PostItem(Context context) {
        this(context,null);
    }

    public PostItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PostItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIsResult=true;
    }

    public void setContent(Post post){
        if(post != null){

            mPost = post;
            new IsFollowReq(this,this,post.getUid());
            ImageUtil.loadImage(mAvatarCiv,post.getUser().getAvatar());
            if(!TextUtils.isEmpty(post.getUser().getNickname())){
                mUserNameTv.setText(post.getUser().getNickname());
            }
            if(!TextUtils.isEmpty(post.getTags())){
                mTagsTv.setText(post.getTags());
            }
            if(post.getUid()==KDangApplication.getInstance().getAccount().getUid()){
                mFollowBtn.setVisibility(GONE);
                mDataTimeTv.setVisibility(VISIBLE);
                if(post.getPosttime()>0){
                    mDataTimeTv.setText(DateTime.format(new Date(post.getPosttime())));
                }
            }else{
                mFollowBtn.setVisibility(VISIBLE);
                mDataTimeTv.setVisibility(GONE);
            }
            if (!TextUtils.isEmpty(post.getContent())) {
                mContentTv.setText(post.getContent());
            }

            if(!TextUtils.isEmpty(post.getAddress())){
                mLocationTv.setText(post.getAddress());
            }

            mPraiseIv.setSelected(post.isGood());
            setPraiseCount(String.valueOf(post.getGoodCount()));
            setCommentCount(String.valueOf(post.getCommentCount()));
            mSeeCountTv.setText("0");

//            mGallery.setImageList(post.getImageList(),
//                    KDangApplication.getInstance().options);

            mNineImageView.setAdapter(mAdapter);
            mNineImageView.setImagesData(post.getImageList());

        }
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            ImageUtil.loadImage(imageView,s, KDangApplication.getInstance().options);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, int index, List<String> list) {
//            Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();
            if(list != null && list.size() > 0)
                LargeRadioActivity.startLargeRadioActivity(getContext(),false,list,index,0);
        }
    };

    public void setIsResult(boolean isResult){
        this.mIsResult=isResult;
    }

    public void setPraiseCount(String praise) {
        mPraiseCountTv.setText(praise);
    }

    public void setCommentCount(String comment) {
        mCommentCountTv.setText(comment);
    }

    @Override
    protected OnClickListener getOnClickListener() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_post:
                break;
            case R.id.img_share:
                break;
            case R.id.civ_avatar:
                break;
            case R.id.btn_follow:
                new FollowPersonReq(this,this,mPost.getUid());
                break;
            case R.id.img_praise:
                break;
            case R.id.img_comment:
                break;
            default:
                break;
        }
    }

    @Override
    public void onParseSuccess(Object object, int id, Object callBackData) {
        switch (id){
            case IsFollowReq.HASH_CODE:
                OtherPersonVo otherPersonVo= (OtherPersonVo) object;
                OtherPerson otherPerson=otherPersonVo.getData();
                Log.d(TAG,otherPerson.toString());
                if(otherPerson==null){
                    mFollowBtn.setVisibility(VISIBLE);
                    mDataTimeTv.setVisibility(GONE);
                    break;
                }
                if(otherPerson.isFollow()){
                    mFollowBtn.setVisibility(GONE);
                    mDataTimeTv.setVisibility(VISIBLE);
                    mDataTimeTv.setText(DateTime.format(new Date(mPost.getPosttime())));
                }
                break;
            case FollowPersonReq.HASH_CODE:
                BooleanVo booleanVo= (BooleanVo) object;
                if(booleanVo.isData()){
                mFollowBtn.setVisibility(GONE);
                mDataTimeTv.setText(DateTime.format(new Date(mPost.getPosttime())));
                setRefresh();
                }else{
                    mFollowBtn.setVisibility(VISIBLE);
                    mDataTimeTv.setText(DateTime.format(new Date(mPost.getPosttime())));
                    setRefresh();
                }
                break;
        }
    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData) {
        Toast.makeText(getContext(),errorInfo,Toast.LENGTH_SHORT).show();
    }

    public void setRefresh() {
        if (mPost == null)
            return;
        mPraiseIv.setSelected(mPost.isGood());
        PostEssenceFragm postEssenceFragm = ActivityContext.get(PostEssenceFragm.class);
        if (postEssenceFragm != null) postEssenceFragm.setRefresh(mPost);

        PostNewFragm postNewFragm = ActivityContext.get(PostNewFragm.class);
        if (postNewFragm != null) postNewFragm.setRefresh(mPost);

        PostFollowFragm postFollowFragm = ActivityContext.get(PostFollowFragm.class);
        if (postFollowFragm != null) postFollowFragm.setRefresh(mPost);
    }
}
