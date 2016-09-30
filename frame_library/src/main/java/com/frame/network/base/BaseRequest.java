package com.frame.network.base;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.frame.network.NetEventCode;
import com.frame.network.bean.NameValueParams;
import com.frame.network.bean.Response;
import com.frame.network.exception.FailSessionException;
import com.frame.network.inter.OnFailSessionObserver;
import com.frame.network.utils.HttpTool;
import com.frame.network.utils.LogUtil;

/**
 * 请求层
 * Created by LDM on 13-12-20.Email : nightkid-s@163.com
 */
public abstract class BaseRequest<T> extends Request<Response<T>> {


    protected List<LoadObserver> loadObservers = new ArrayList<LoadObserver>(); //加载状态的观察者集合

    protected List<OnFailSessionObserver> failObservers = new ArrayList<OnFailSessionObserver>(); //会话异常处理接口

    protected abstract String getUrl(); //获取连接地址

    protected abstract List<NameValueParams> getParams(); //获取请求参数

    protected abstract T parseResult(String result); //解析数据

    protected abstract void handleResult(T result) throws FailSessionException, FailSessionException; //处理解析出来的数据

    protected abstract String getCooike();
    
    private String rawData;
    StringBuilder data;
    boolean isSuccessful = false;
    boolean isNetWork = true;

    //加载状态反馈
    public interface LoadObserver {  	
        void onPreLoadObserver(); //加载前
        void onLoadFinishObserver(); //加载完成 
    }

    @Override
    protected void preRequest() {
    	isNetWork = true;
        for (LoadObserver observer : loadObservers) observer.onPreLoadObserver();
       
    }

    @Override
    protected Response<T> doRequest() {
        /**联网获取数据，并记录好联网成功/失败的事件*/
    	if (isNetWork) {
    		isSuccessful = false;
            StringBuilder responseInfo = new StringBuilder();
            data = new StringBuilder();
            try {   	
            	rawData = HttpTool.post(getUrl(), getParams(), getCooike());
                data.append(rawData);
                isSuccessful = true;
            } catch (RuntimeException e) {
                e.printStackTrace();
                rawData = null;
                responseInfo.append(e.getMessage());
            }

            return new Response<T>(isSuccessful, responseInfo.toString(), isSuccessful ? parseResult(data.toString()) : null);
		}
    	return null;
    }

    @Override
    protected void doRequestComplete(Response<T> response){
    	if (isNetWork) {
    		  for (LoadObserver observer : loadObservers) observer.onLoadFinishObserver();
    	        try {
    	            if (response.getIsSuccessful()) handleResult(response.getResult());
    	            else throw new FailSessionException(response.getResponseInfo(), NetEventCode.CODE_FAIL_REQUEST);
    	        }catch (FailSessionException e){
    	            for (OnFailSessionObserver failObserver : failObservers) failObserver.onFailSession(e.getMessage(), e.getFailCode());
    	            LogUtil.e("http_", String.format("会话失败： %s  失败类型: %d 接口信息:  %s", e.getMessage(), e.getFailCode(), getUrl()));
    	        }
		}
     
    }

    public void registerLoadObserver(LoadObserver observer) {
        if (observer != null) loadObservers.add(observer);
    }

    public void unRegisterLoadObserver(LoadObserver observer) {
        if (observer != null) loadObservers.remove(observer);
    }

    public void registerFailObserver(OnFailSessionObserver failObserver) {
        if(failObserver != null) failObservers.add(failObserver);
    }

    public void unRegisterFailObserver(OnFailSessionObserver failObserver) {
        if(failObserver != null) failObservers.remove(failObserver);
    }

    public String getRawData() {
        return rawData == null ? "" : rawData.trim();
    }

    public void loadLocalData(String data){
        if( ! TextUtils.isEmpty(data)) doRequestComplete(new Response<T>(true, "", parseResult(data)));
    }


    public String getCompleteUrl(){
        return HttpTool.constructUrl(getUrl(), getParams());
    }
}
