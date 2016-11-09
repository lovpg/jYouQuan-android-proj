package com.lbsm.kdang.net.request;

import android.text.TextUtils;

import com.frame.network.bean.NameValueParams;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.lbsm.kdang.entity.vo.BooleanVo;
import com.lbsm.kdang.net.HttpUrl;
import com.lbsm.kdang.net.request.base.Request;

import org.json.JSONException;

import java.io.File;
import java.util.List;

/**
 * date: 2016/10/30.
 */

public class SendPostReq extends Request<BooleanVo> {

    private String mAddress;
    private String mLocation;
    private String mContent;
    private String mTags;
    private List<String> mImageList;

    public SendPostReq(OnParseObserver2<? super BooleanVo> parseObserver,
                       OnFailSessionObserver2 failSessionObserver) {
        registerFailObserver(failSessionObserver);
        registerParserObserver(parseObserver);

    }

    public void setApiParameters(String address,String location,
                                 String content,String tags,List<String> imageList) {
        this.mAddress = address;
        this.mLocation = location;
        this.mContent = content;
        this.mTags=tags;
        this.mImageList = imageList;
        startRequest();
    }

    @Override
    protected BooleanVo parseGsonBody(String body) throws JSONException {
//        return new Gson().fromJson(body, BooleanVo.class);
        return new BooleanVo(true);
    }

    @Override
    protected void setParams(List<NameValueParams> params) {
        if (!TextUtils.isEmpty(mAddress)) {
            params.add(new NameValueParams("address", mAddress));
        }

        if (!TextUtils.isEmpty(mLocation)) {
            params.add(new NameValueParams("location", mLocation));
        }

        if (!TextUtils.isEmpty(mContent)) {
            params.add(new NameValueParams("content", mContent));
        }

        if(!TextUtils.isEmpty(mContent)){
            params.add(new NameValueParams("tags",mTags));
        }

        if (mImageList != null && mImageList.size() > 0) {
            for (int i = 0; i < mImageList.size(); i++) {
                params.add(new NameValueParams("file", new File(mImageList.get(i))));
            }
        }
    }

    @Override
    protected String getApi() {
        return HttpUrl.SEND_POST;
    }
}
