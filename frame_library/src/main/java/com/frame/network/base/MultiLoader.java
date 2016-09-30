package com.frame.network.base;

import java.util.ArrayList;
import java.util.List;
import com.frame.network.inter.OnFailSessionObserver;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnLoadObserver2;
import com.frame.network.inter.OnParseObserver;
import com.frame.network.inter.OnParseObserver2;


/**
 * 为了减少代码量，用于请求回调和共用解析接口
 * 采用接口适配模式，兼容旧接口，做一个接口翻译，扩展功能（回调请求对象的自身实例信息）
 * 不同实例可以共用OnParseObserver2<Object> 接口
 * Created by LDM on 14-4-9.Email : nightkid-s@163.com
 */
public abstract class MultiLoader<T> extends Loader<T> implements OnFailSessionObserver
        , OnParseObserver<T>, BaseRequest.LoadObserver{

    private List<OnFailSessionObserver2> failListeners = new ArrayList<OnFailSessionObserver2>();
    private List<OnParseObserver2<? super T>> parseObservers = new ArrayList<OnParseObserver2<? super T>>();
    private List<OnLoadObserver2> loadObservers = new ArrayList<OnLoadObserver2>();
    private int requestId = this.getClass().getSimpleName().hashCode();
    private Object callBackData;

    public MultiLoader() {
        this(null);
    }

    public MultiLoader(OnFailSessionObserver2 failListener) {
        this(failListener, null);
    }

    public MultiLoader(OnFailSessionObserver2 failListener, int requestId, Object callBackData) {
        this(failListener, callBackData);
        this.requestId = requestId;
    }

    public MultiLoader(OnFailSessionObserver2 failListener, Object callBackData) {
        this(failListener, callBackData, null, null);
    }

    public MultiLoader(OnFailSessionObserver2 failListener, Object callBackData, OnLoadObserver2 loadObserver, OnParseObserver2<? super T> parseObserver) {
        this(failListener, callBackData, loadObserver, parseObserver, true, false);
    }

    public MultiLoader(OnFailSessionObserver2 failListener, OnLoadObserver2 loadObserver, OnParseObserver2<? super T> parseObserver) {
        this(failListener, null, loadObserver, parseObserver, true, false);
    }

    public MultiLoader(OnFailSessionObserver2 failListener, Object callBackData, OnLoadObserver2 loadObserver, OnParseObserver2<? super T> parseObserver, boolean reLogin, boolean cache) {
        super(reLogin, cache);
        if(failListener != null) this.failListeners.add(failListener);
        this.callBackData = callBackData;
        registerFailObserver(this);
        registerParserObserver(this);
        registerLoadObserver(this);
        registerLoadObserver2(loadObserver);
        registerParserObserver(parseObserver);
    }

    @Override
    public void onFailSession(String errorInfo, int failCode) {
    	this.onClean();
        for (OnFailSessionObserver2 observer2 : failListeners)
            if(observer2 != null) observer2.onFailSession(errorInfo, failCode, requestId, callBackData);
    }

    @Override
    public void onParseSuccess(T t) {
        for(OnParseObserver2<? super T> parseObserver : parseObservers)
            parseObserver.onParseSuccess(t, requestId, callBackData);
    }

    @Override
    public void onPreLoadObserver() {
        for(OnLoadObserver2 observer : loadObservers)
            observer.onPreLoadObserver(requestId);
    }

    @Override
    public void onLoadFinishObserver() {
    	this.onClean();
        for(OnLoadObserver2 observer : loadObservers)
            observer.onLoadFinishObserver(requestId, callBackData);
    }

    public void registerParserObserver(OnParseObserver2<? super T> parser){
        if(parser != null) parseObservers.add(parser);
    }

    public void unRegisterParserObserver(OnParseObserver2<? super T> parser){
        if(parser != null) parseObservers.remove(parser);
    }

    public void registerLoadObserver2(OnLoadObserver2 observer){
        if(observer != null) loadObservers.add(observer);
    }

    public void unregisterLoadObserver2(OnLoadObserver2 observer){
        if(observer != null) loadObservers.remove(observer);
    }

    public void registerFailObserver(OnFailSessionObserver2 failObserver) {
        if(failObserver != null) failListeners.add(failObserver);
    }

    public void unRegisterFailObserver(OnFailSessionObserver2 failObserver) {
        if(failObserver != null) failListeners.remove(failObserver);
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setCallBackData(Object callBackData) {
        this.callBackData = callBackData;
    }

    public Object getCallBackData() {
        return callBackData;
    }

    public void setReqIdAndCbData(int id, Object callBackData){
        this.requestId = id;
        this.callBackData = callBackData;
    }
}
