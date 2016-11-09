package com.lbsm.kdang.ui.fragments.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.lbsm.kdang.R;
import com.lbsm.kdang.ui.fragments.listener.OnPraiseCountListener;
import com.lbsm.kdang.widget.CircleImageView;

/**
 * date: 2016/11/2.
 */

public abstract class BaseItem extends LinearLayout {

    private Context mContext;

    protected ImageView mPraiseIv;
    protected ImageView mCommentIv;
    protected ImageView mShareIv;

    protected CircleImageView mAvatarCiv;
    protected TextView mUserNameTv;
    protected TextView mTagsTv;
    protected Button mFollowBtn;
    protected TextView mDataTimeTv;
    protected TextView mContentTv;
    protected NineGridImageView mNineImageView;
    protected TextView mLocationTv;
    protected TextView mPraiseCountTv;
    protected TextView mCommentCountTv;
    protected TextView mSeeCountTv;

    protected View mPostItemView;



    public BaseItem(Context context) {
        this(context, null);
    }

    public BaseItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        inflater.inflate(R.layout.item_post_essence,this);
        mPraiseIv= (ImageView) findViewById(R.id.img_praise);
        mCommentIv= (ImageView) findViewById(R.id.img_comment);
        mShareIv= (ImageView) findViewById(R.id.img_share);
        mAvatarCiv = (CircleImageView) findViewById(R.id.civ_avatar);
        mUserNameTv= (TextView) findViewById(R.id.tv_username);
        mTagsTv= (TextView) findViewById(R.id.tv_tags);
        mFollowBtn= (Button) findViewById(R.id.btn_follow);
        mDataTimeTv= (TextView) findViewById(R.id.tv_data_time);
        mContentTv= (TextView) findViewById(R.id.tv_content);
        mNineImageView= (NineGridImageView) findViewById(R.id.ngimg_nine_imageview);
        mLocationTv= (TextView) findViewById(R.id.tv_location);
        mPraiseCountTv= (TextView) findViewById(R.id.tv_praise_count);
        mCommentCountTv= (TextView) findViewById(R.id.tv_comment_count);
        mSeeCountTv= (TextView) findViewById(R.id.tv_see_count);

        mPostItemView=findViewById(R.id.item_post);

        mPostItemView.setOnClickListener(getOnClickListener());
        mPraiseIv.setOnClickListener(getOnClickListener());
        mCommentIv.setOnClickListener(getOnClickListener());
        mShareIv.setOnClickListener(getOnClickListener());
        mAvatarCiv.setOnClickListener(getOnClickListener());
        mFollowBtn.setOnClickListener(getOnClickListener());
    }


    protected abstract OnClickListener getOnClickListener();

    protected OnPraiseCountListener mPraiseCountListener;

    public void setonPraiseCountListener(OnPraiseCountListener praiseCountListener) {
        this.mPraiseCountListener = praiseCountListener;
    }

//    protected void setUserDate(User user) {
//        circleAvatarView.setUrl(SimpleUser.setSimpleUser(user));
//        datumView.setContent(user, -1);
//    }

//    protected void setUserDate(SimpleUser simpleUser) {
//        mAvatarCiv.setUrl(simpleUser);
//
//        datumView.setContent(simpleUser, -1);
//    }
//
//    public void setAddres(String address) {
//        if (!TextUtils.isEmpty(address)) {
//            viewAddress.setVisibility(View.VISIBLE);
//            mAddress.setText(address);
//        }
//
//    }

}
