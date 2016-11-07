package com.lbsm.kdang.net.request;

import com.frame.network.bean.NameValueParams;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.google.gson.Gson;
import com.lbsm.kdang.entity.vo.BooleanVo;
import com.lbsm.kdang.net.HttpUrl;
import com.lbsm.kdang.net.request.base.Request;

import org.json.JSONException;

import java.util.List;

/**
 * date: 2016/11/2.
 */

public class FollowPersonReq extends Request<BooleanVo> {

    private long uid;

    public static final int HASH_CODE = 654023736;

    public FollowPersonReq(OnParseObserver2<? super BooleanVo> parseObserver,
                           OnFailSessionObserver2 failSessionObserver, long uid) {
        registerFailObserver(failSessionObserver);
        registerParserObserver(parseObserver);
        this.uid = uid;
        startRequest();
    }

    @Override
    protected BooleanVo parseGsonBody(String body) throws JSONException {
        return new Gson().fromJson(body, BooleanVo.class);
    }

    @Override
    protected void setParams(List<NameValueParams> params) {
        if (uid > 0) {
            params.add(new NameValueParams("uid", String.valueOf(uid)));
        }

    }

    @Override
    protected String getApi() {
        return HttpUrl.FOLLOW_PERSON;
    }
}
