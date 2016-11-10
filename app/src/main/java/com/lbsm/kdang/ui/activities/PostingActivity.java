package com.lbsm.kdang.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.lbsm.kdang.R;
import com.lbsm.kdang.base.BaseActivity;
import com.lbsm.kdang.entity.vo.BooleanVo;
import com.lbsm.kdang.net.request.SendPostReq;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.utils.Constants;
import com.lbsm.kdang.widget.SharePhotoInput;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.darsh.multipleimageselect.helpers.Constants.REQUEST_CODE;

/**
 * date: 2016/10/29.
 */

public class PostingActivity extends BaseActivity {

    @Bind(R.id.posting_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.btn_submit)
    ImageView mSubmitBtn;
    @Bind(R.id.edt_content)
    EditText mContentEdt;
    @Bind(R.id.spi_photo_input)
    SharePhotoInput mPhotoInput;

    private List<LocalFile> mLocalFiles;
    private List<String> mUrls = null;
    private String mContent;

    private SendPostReq mSendPostReq;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mSendPostReq.setApiParameters("发送位置", "0,0", mContent, "挖掘机", mUrls);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.red_200), 50);
        initView();
    }

    private void initView() {
        mUrls = new ArrayList<>();
        mSendPostReq = new SendPostReq(this, this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mLoadingDialog.dismiss();
        super.onDestroy();
    }

    @OnClick(R.id.btn_submit)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                submitPost();
                break;
        }
    }

    private void submitPost() {
        mContent = mContentEdt.getText().toString().trim();
        if (TextUtils.isEmpty(mContent)) {
            showLongToast("Please enter the post content");
            return;
        }
        mLoadingDialog.show();
        mUrls.clear();

        if (mPhotoInput.getLocalFiles() != null && mPhotoInput.getLocalFiles().size() > 0) {
            new Thread() {
                @Override
                public void run() {
                    boolean isCom = true;
                    while (isCom) {
                        if (mPhotoInput.isCompress()) {
                            isCom = false;
                            List<LocalFile> localFiles = mPhotoInput.getLocalFiles();
                            for (LocalFile localFile : localFiles) {
                                mUrls.add(localFile.getCompressUri());
                            }
                            mHandler.sendEmptyMessage(0);
                        } else {
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }.start();
        } else {
            mHandler.sendEmptyMessage(0);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case Constants.IMAGE_SELECT:
                if (data != null) {
                    boolean isCamera = data.getBooleanExtra("isCamera", false);
                    if (isCamera) {
                        LocalFile localFile = (LocalFile) data.getSerializableExtra("localFile");
                        mPhotoInput.addLocalFile(localFile);
                    } else {
                        mLocalFiles = (List<LocalFile>) data.getSerializableExtra("localFiles");
                        Log.d("PostingActivity", "mLocalFiles.size():" + mLocalFiles.size());
                        if (mLocalFiles != null && mLocalFiles.size() > 0) {
                            Log.d("PostingActivity", "mLocalFiles:" + mLocalFiles.get(0).getThumbnaiPath());
                            mPhotoInput.addAllLocalFile(mLocalFiles);
                        }

                    }

                    mLocalFiles = null;
                }
                break;
            case Constants.IMAGE_DELETE:
                if (data != null) {
                    mLocalFiles = new ArrayList<LocalFile>();
                    List<String> list = data.getStringArrayListExtra("uris");
                    for (String str : list) {
                        for (LocalFile localFile : mPhotoInput.getLocalFiles()) {
                            if (str.equals(localFile.getThumbnailUri()) || str.equals(localFile.getCompressUri())) {
                                mLocalFiles.add(localFile);
                                break;
                            }
                        }
                    }
                    mPhotoInput.cleanFiles();
                    mPhotoInput.addAllLocalFile(mLocalFiles);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onParseSuccess(Object t, int id, Object callBackData) {
        mLoadingDialog.dismiss();
        BooleanVo boolVO = (BooleanVo) t;
        if (boolVO.isData()) {
            showLongToast("Post share success");
            setResult(REQUEST_CODE);
            finish();
            //            ChosseActivity chosseActivity = ActivityContext.get(ChosseActivity.class);
            //            if(chosseActivity != null) chosseActivity.finish();
            //            finishRight();
            //            //            MeFragment meFragment = ActivityContext.get(MeFragment.class);
            //            //            if(meFragment != null) meFragment.onRefresh();
            //
            //            MakeFriendsFragment makeFriendsFragment = ActivityContext.get(MakeFriendsFragment.class);
            //            if(makeFriendsFragment != null) makeFriendsFragment.onRefresh();
        }

    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData) {
        mLoadingDialog.dismiss();
        showShortToast(errorInfo);
    }

}
