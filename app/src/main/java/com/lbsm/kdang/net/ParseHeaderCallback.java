package com.lbsm.kdang.net;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zrf on 2016/10/16.
 */

public class ParseHeaderCallback extends Callback {

    @Override
    public Object parseNetworkResponse(Response response, int id) throws Exception {

        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(Object response, int id) {

    }
}
