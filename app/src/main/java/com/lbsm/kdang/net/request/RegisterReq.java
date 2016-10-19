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

    private String mUserName;
    private String mPassword;
    private String mCode;
    private String mImagePath;



    public RegisterReq(OnParseObserver2<? super LoginAuthVo> onParseObserver2, OnFailSessionObserver2 failSessionObserver) {
        // TODO Auto-generated constructor stub
        registerFailObserver(failSessionObserver);
        registerParserObserver(onParseObserver2);
    }

    public void setApiParameters(String userName, String password, String imagePath, String code) {
        this.mUserName = userName;
        this.mPassword = password;
        this.mImagePath = imagePath;
        this.mCode = code;
        startRequest();
    }

    @Override
    protected LoginAuthVo parseGsonBody(String body) throws JSONException {
        // TODO Auto-generated method stub
        return new Gson().fromJson(body, LoginAuthVo.class);
    }

    @Override
    protected void setParams(List<NameValueParams> params) {
        // TODO Auto-generated method stub
        params.add(new NameValueParams("username", mUserName));
        params.add(new NameValueParams("password", mPassword));
        params.add(new NameValueParams("code", mCode));
        params.add(new NameValueParams("file", new File(mImagePath)));
    }

    @Override
    protected String getApi() {
        // TODO Auto-generated method stub
        return HttpUrl.REGISTER;
    }

    @Override
    protected String getCooike() {
        // TODO Auto-generated method stub
        return null;
    }

}
