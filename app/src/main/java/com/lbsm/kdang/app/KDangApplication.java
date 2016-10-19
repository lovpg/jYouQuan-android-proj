package com.lbsm.kdang.app;

import com.facebook.stetho.Stetho;
import com.lbsm.kdang.net.MyHttpClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by zrf on 2016/10/13.
 * 初始化Stetho：通过Chrome抓包
 */

public class KDangApplication extends YCApplication {

    private static  final String TAG="KDangApplication";
    private static KDangApplication kDangApplication;

    @Override
  public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        MyHttpClient.initInstance();

        kDangApplication=this;

//        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
//                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    public static KDangApplication getInstance(){
        return kDangApplication;
    }

}
