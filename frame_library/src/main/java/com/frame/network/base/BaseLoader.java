package com.frame.network.base;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.frame.network.NetEventCode;
import com.frame.network.bean.NameValueParams;
import com.frame.network.bean.Result2;
import com.frame.network.exception.FailSessionException;
import com.frame.network.inter.OnParseObserver;
import com.frame.network.utils.Utils;

/**
 * 解析层
 * Created by LDM on 13-12-31.Email : nightkid-s@163.com
 */
public abstract class BaseLoader<T> extends BaseRequest<Result2<T>> {

    private List<OnParseObserver<T>> parseObservers = new ArrayList<OnParseObserver<T>>(); //解析器观察者集合

    protected abstract T parseBody(String body) throws JSONException; //解析body数据体

    protected abstract void setParams(List<NameValueParams> params); //设置额外的请求参数

    protected abstract String getApi(); //调用接口，不包括主连接前缀

    
//    public BaseLoader() {
//        this(true, false); //默认开启重登录处理，但不开启缓存处理
//    }

    public BaseLoader(boolean reLogin, boolean cache) {
//        if(cache) new CacheWrapper<BaseLoader<T>>(this);     //开启缓存处理
//        if(reLogin) new ReLoginWrapper<BaseLoader<T>>(this); //开启针对session过期重登录处理
    }

    @Override
    protected Result2<T> parseResult(String result) {
        try {
        	Result2<T> result2 = parseHeader(new JSONObject(result));
            return result2;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Result2<T>(NetEventCode.CODE_FAIL_PARSE, "返回数据格式错误", getUrl(), null);
    }

    @Override
    protected void handleResult(Result2<T> result) throws FailSessionException {
        if (result.getErrorCode() == NetEventCode.CODE_SUCCESS)
            for (OnParseObserver<T> observer : parseObservers) {
                if (observer != null) observer.onParseSuccess(result.getBody());
            }
        else throw new FailSessionException(result.getErrorInfo(), result.getErrorCode());
    }

    @Override
    protected String getUrl() {
        return  getApi();
    }

    @Override
    protected List<NameValueParams> getParams() {
        List<NameValueParams> params = new ArrayList<NameValueParams>();
        //you can add some fixed params here//
//        String session = AppManager.getInstance().getAccount().getUser().getSessionId();
//        if(!TextUtils.isEmpty(session)) params.add(new NameValueParams("sessionId", session));
//        params.add(new NameValueParams("lfrom", "1"));
        setParams(params);
        return params;
    }

    public void registerParserObserver(OnParseObserver<T> observer) {
        if (observer != null) parseObservers.add(observer);
    }

    public void unregisterParserObserver(OnParseObserver<T> observer) {
        if (observer != null) parseObservers.remove(observer);
    }

    protected Result2<T> parseHeader(JSONObject object) throws JSONException{
        int error_code = Utils.getErrorCode((object.has("status") ? object.getString("status") : ""));
        String error_info = object.has("message") ? object.getString("message") : "";
        String request_url = getUrl();
        return new Result2<T>(error_code, error_info, request_url, error_code != NetEventCode.CODE_SUCCESS ? null : parseBody(object.toString()));
    }
}
