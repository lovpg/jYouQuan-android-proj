package com.lbsm.kdang.net.request;

import com.frame.network.base.MultiLoader;
import com.frame.network.bean.NameValueParams;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.google.gson.Gson;
import com.lbsm.kdang.entity.vo.BooleanVo;
import com.lbsm.kdang.net.HttpUrl;

import org.json.JSONException;

import java.util.List;

/**
 * date: 2016/10/18.
 */

public class SendVerifyCodeReq extends MultiLoader<BooleanVo>{

    public static final int HASH_CODE = 1839829488;

    private String mPhoneNumber;



    public SendVerifyCodeReq(OnParseObserver2<? super BooleanVo> onParseObserver2, OnFailSessionObserver2 failSessionObserver) {
        // TODO Auto-generated constructor stub
        registerFailObserver(failSessionObserver);
        registerParserObserver(onParseObserver2);
    }

    public void setApiParameters(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
        startRequest();
    }

    @Override
    protected BooleanVo parseGsonBody(String body) throws JSONException {
        return new Gson().fromJson(body, BooleanVo.class);
    }

    @Override
    protected void setParams(List<NameValueParams> params) {
        params.add(new NameValueParams("mobile", mPhoneNumber));
    }

    @Override
    protected String getApi() {
        return HttpUrl.SEND_VERIFY_CODE;
    }

    @Override
    protected String getCookie() {
        return null;
    }

}
