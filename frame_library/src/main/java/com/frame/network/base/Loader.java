package com.frame.network.base;

import org.json.JSONException;


/**
 * 解析DataList只有一个json对象的网络数据
 * Created by LDM on 14-1-9.Email : nightkid-s@163.com
 */
public abstract class Loader<T> extends BaseLoader<T> {

//    protected Loader() {
//    }

    protected Loader(boolean reLogin, boolean cache) {
        super(reLogin, cache);
    }

    protected abstract T parseGsonBody(String body) throws JSONException; //解析单个个体

    @Override
    protected T parseBody(String body) throws JSONException {
        return parseGsonBody(body);
    }

}
