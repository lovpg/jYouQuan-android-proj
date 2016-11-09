package com.lbsm.kdang.net.request;

import com.frame.network.bean.NameValueParams;
import com.google.gson.Gson;
import com.lbsm.kdang.entity.vo.PostVo;
import com.lbsm.kdang.net.HttpUrl;
import com.lbsm.kdang.net.request.base.PageLoader;

import org.json.JSONException;

import java.util.List;

/**
 * date: 2016/11/2.
 */

public class PostFollowReq extends PageLoader<PostVo>{
    @Override
    protected void setNoPageParams(List<NameValueParams> params) {

    }

    @Override
    protected PostVo parseGsonBody(String body) throws JSONException {
        return new Gson().fromJson(body,PostVo.class);
    }

    @Override
    protected String getApi() {
        return HttpUrl.POST_FOLLOW;
    }

    @Override
    public void onParseSuccess(PostVo postVo) {
        //        if(mPageId == 1) PostDb.insertPageList(postVo.getData(),HttpUrl.POST_ESSENCE);
        //        else PostDb.insertList(postVo.getData());

        super.onParseSuccess(postVo);
    }
}
