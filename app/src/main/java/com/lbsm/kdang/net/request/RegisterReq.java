package com.lbsm.kdang.net.request;

import com.frame.network.base.MultiLoader;
import com.frame.network.bean.NameValueParams;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.google.gson.Gson;
import com.lbsm.kdang.entity.vo.LoginAuthVo;
import com.lbsm.kdang.net.HttpUrl;

import org.json.JSONException;

import java.io.File;
import java.util.List;

public class RegisterReq extends MultiLoader<LoginAuthVo> {

    public static final int HASH_CODE = -1228650458;

    private String mPhoneNumber;
    private String mPassword;
    private String mCode;
    private String mImagePath;


    public RegisterReq(OnParseObserver2<? super LoginAuthVo> onParseObserver2, OnFailSessionObserver2 failSessionObserver) {
        registerFailObserver(failSessionObserver);
        registerParserObserver(onParseObserver2);
    }

    public void setApiParameters(String phoneNumber, String password, String code, String imagePath) {
        this.mPhoneNumber = phoneNumber;
        this.mPassword = password;
        this.mCode = code;
        this.mImagePath = imagePath;
        startRequest();
    }

    @Override
    protected LoginAuthVo parseGsonBody(String body) throws JSONException {
        return new Gson().fromJson(body, LoginAuthVo.class);
    }

    @Override
    protected void setParams(List<NameValueParams> params) {
        params.add(new NameValueParams("username", mPhoneNumber));
        params.add(new NameValueParams("password", mPassword));
        params.add(new NameValueParams("code", mCode));
        params.add(new NameValueParams("file", new File(mImagePath)));
    }

    @Override
    protected String getApi() {
        return HttpUrl.REGISTER;
    }

    @Override
    protected String getCookie() {
        return null;
    }

}
