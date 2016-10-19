/*
 * Copyright (C) 14-6-6 下午3:42 Guangzhou Visions Tech Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frame.network.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Use:
 * 
 * Created by yy3302038@sina.com 15.03.05
 */
public class NetworkUtil {

    private NetworkUtil() {}

    /**
     * 网络是否可用
     * @param context
     * @return true if the network is available, false otherwise
     */
    public static boolean isNetworkAwailable(Context context) {
        if(context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info == null) {
            return false;
        }
        return info.isAvailable();
    }

    /**
     * 网络是否已经连接
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if(context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info == null) {
            return false;
        }
        return info.isConnected();
    }

    /**
     * WIFI网络是否可用
     * @param context
     * @return
     */
    public static boolean isWifiAwailable(Context context) {
        if(context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(info == null) {
            return false;
        }
        return info.isAvailable();
    }


    /**
     * WIFI网络是否已连接
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if(context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(info == null) {
            return false;
        }
        return info.isConnected();
    }

    /**
     * 获取网络连接类型
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        if(context == null) {
            return -1;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(info == null) {
            return -1;
        }
        return info.getType();
    }
    
    public static String getLocalIpv4Address() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.
                                isIPv4Address(inetAddress.getHostAddress())) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    @TargetApi(11)
    public static boolean is4GNetwork(Context cxt){
        boolean isOpened4G = false;
        TelephonyManager telephonyManager = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= 11) {
            isOpened4G = telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE;
        }
        boolean isMobile = false;
        ConnectivityManager cm = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        isMobile = info == null ? false : info.isConnected();
        return isOpened4G && isMobile;
    }
}
