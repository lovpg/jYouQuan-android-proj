package com.frame.network.inter;

/**
 * 回调接口
 * Created by LDM on 2014/5/6.Email : nightkid-s@163.com
 */
public interface OnLoadObserver2 {
    void onPreLoadObserver(int id); //加载前

    void onLoadFinishObserver(int id, Object callbackData); //加载完成
}
