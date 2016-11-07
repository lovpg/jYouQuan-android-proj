package com.lbsm.kdang.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * date: on 2016/10/13.
 */

public class NetUtil {

    public static final String NETWORK_ERROR = "not network connect";

    public static boolean isNetWorkConnected(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            return info.isAvailable();
        }
        return false;
    }

}
