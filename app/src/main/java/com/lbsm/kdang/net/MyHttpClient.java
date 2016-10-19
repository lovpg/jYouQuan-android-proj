package com.lbsm.kdang.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Map;
import java.util.Set;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zrf on 2016/10/13.
 */

public class MyHttpClient {

    private static OkHttpClient mHttpClient;

    public MyHttpClient(){
    }

    public static void initInstance(){
        if(mHttpClient==null){
            mHttpClient=new OkHttpClient.Builder().addNetworkInterceptor(
                    new StethoInterceptor()).build();
        }
    }

    //get
    public static void httpGet(String url, Callback listener) {
        Request request = new Request.Builder().url(url).tag(url).build();
        mHttpClient.newCall(request).enqueue(listener);
    }

    //post
    public static void httpPost(String url, Map<String,String> params, Callback listener){
        if(params==null){
            httpGet(url,listener);
            return;
        }
        //okhttp 3.0+
        FormBody.Builder builder=new FormBody.Builder();
        //        okhttp2.0+
        //        FormEncodingBuilder builder=new FormEncodingBuilder();
        Set<Map.Entry<String,String>> entrySet=params.entrySet();
        for(Map.Entry<String,String> entry:entrySet){
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody requestBody=builder.build();
        Request request=new Request.Builder().url(url).post(requestBody).
                tag(url).build();
        mHttpClient.newCall(request).enqueue(listener);
    }
}