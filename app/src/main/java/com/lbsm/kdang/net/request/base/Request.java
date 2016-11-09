package com.lbsm.kdang.net.request.base;


import com.frame.network.base.MultiLoader;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnLoadObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.google.gson.Gson;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.preference.LoginAuthPref;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;

public abstract class Request<T> extends MultiLoader<T> {

    protected Request() {
        super();
    }

    protected Request(OnFailSessionObserver2 failListener) {
        super(failListener);
    }

    protected Request(OnFailSessionObserver2 failListener, int requestId, Object callBackData) {
        super(failListener, requestId, callBackData);
    }

    protected Request(OnFailSessionObserver2 failListener, Object callBackData) {
        super(failListener, callBackData);
    }

    protected Request(OnFailSessionObserver2 failListener, Object callBackData, OnLoadObserver2 loadObserver, OnParseObserver2<? super T> parseObserver) {
        super(failListener, callBackData, loadObserver, parseObserver);
    }

    protected Request(OnFailSessionObserver2 failListener, OnLoadObserver2 loadObserver, OnParseObserver2<? super T> parseObserver) {
        super(failListener, loadObserver, parseObserver);
    }

    protected Request(OnFailSessionObserver2 failListener, Object callBackData, OnLoadObserver2 loadObserver, OnParseObserver2<? super T> parseObserver, boolean reLogin, boolean cache) {
        super(failListener, callBackData, loadObserver, parseObserver, reLogin, cache);
    }

    @Override
    protected String getCookie() {
        LoginAuthPref loginAuthPref = KDangApplication.getInstance().getAccount();
        String cookie = null;
        try {
            cookie = "uid=" + loginAuthPref.getUid() + ",token="
                    + loginAuthPref.getToken() + ",SESSIONID=" + loginAuthPref.getSessionId()
                    + ",username=" + URLEncoder.encode(loginAuthPref.getUsername(), "UTF-8")+",type=android,ver=7";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return cookie;
    }
//
//    protected String getDomain() {
//        return HttpUrl.BASE_URL;
//    }

    @Override
    protected T parseGsonBody(String body) throws JSONException {
        return new Gson().fromJson(body, getType(getClass()));
    }

    private Class<T> getGeneric(Class<?> c) {
        Class<T> cls;
        if (c.getGenericSuperclass() instanceof ParameterizedType)
            cls = (Class<T>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
        else
            cls = getGeneric(c.getSuperclass());
        return cls;
    }

    private Type getType(Class<?> c) {
        Type type;
        if (c.getGenericSuperclass() instanceof ParameterizedType)
            type = ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
        else
            type = getGeneric(c.getSuperclass());
        return type;
    }

}
