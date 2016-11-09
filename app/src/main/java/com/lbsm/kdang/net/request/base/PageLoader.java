package com.lbsm.kdang.net.request.base;

import com.frame.network.bean.NameValueParams;
import com.frame.network.bean.Result2;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnLoadObserver2;
import com.frame.network.inter.OnParseObserver2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * date: 2016/10/25.
 */

public abstract class PageLoader<T> extends Request<T> {

    private boolean mNext;
    protected int mPageId = 1;
    protected int mSize = 10;

    protected PageLoader() {
        super();
    }

    protected PageLoader(OnFailSessionObserver2 failListener, OnLoadObserver2 loadObserver,
               OnParseObserver2<? super T> parseObserver) {
        super(failListener, loadObserver, parseObserver);
    }

    public void loadPage(int pageId) {
        loadPage(pageId, mSize);
    }

    public void loadPage(int pageId, int size) {
        mPageId = pageId;
        mSize = size;
        startRequest();
    }

    @Override
    protected void setParams(List<NameValueParams> params) {
        params.add(new NameValueParams("pageId", String.valueOf(mPageId)));
        params.add(new NameValueParams("size", String.valueOf(mSize)));
        setNoPageParams(params);
    }

    protected abstract void setNoPageParams(List<NameValueParams> params);

    @Override
    protected Result2<T> parseHeader(JSONObject object) throws JSONException {
        mNext = object.optBoolean("next", false);
        return super.parseHeader(object);
    }

    public void loadFirstPage() {
        loadPage(1);
    }

    public void loadNextPage() {
        loadPage(mPageId + 1);
    }

    public int getPageId() {
        return mPageId;
    }

    public boolean isNext() {
        return mNext;
    }

    public void setSize(int size) {
        mSize = size;
    }
}
