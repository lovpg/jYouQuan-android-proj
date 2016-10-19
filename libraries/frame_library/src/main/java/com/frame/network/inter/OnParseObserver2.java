package com.frame.network.inter;

/**
 * 解析接口
 * T 解析的数据载体
 * Created by LDM on 14-4-9.Email : nightkid-s@163.com
 */
public interface OnParseObserver2<T> {
    public void onParseSuccess(T t, int id, Object callBackData);
}
