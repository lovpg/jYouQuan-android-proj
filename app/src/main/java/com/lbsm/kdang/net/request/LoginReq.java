package com.lbsm.kdang.net.request;

import com.frame.network.base.MultiLoader;
import com.frame.network.bean.NameValueParams;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.google.gson.Gson;
import com.lbsm.kdang.entity.vo.LoginAuthVo;
import com.lbsm.kdang.net.HttpUrl;

import org.json.JSONException;

import java.util.List;

public class LoginReq extends MultiLoader<LoginAuthVo> {

    public static final int HASH_CODE = 2087391381;
    private String mPhoneNumber;
    private String mPassword;

    public LoginReq(OnParseObserver2<? super LoginAuthVo> parseObserver2, OnFailSessionObserver2 failSessionObserver2) {
        registerParserObserver(parseObserver2);
        registerFailObserver(failSessionObserver2);
    }

    public void setApiParameters(String phoneNumber, String password) {
        this.mPhoneNumber = phoneNumber;
        this.mPassword = password;
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
    }

    @Override
    protected String getApi() {
        return HttpUrl.LOGIN;
    }

    @Override
    protected String getCookie() {
        return null;
    }

}
