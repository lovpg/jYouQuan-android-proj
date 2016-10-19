package com.frame.network.inter;

/**
 * 会话失败的回调接口, 失败原因可能包括联网失败或者解析失败
 * Created by LDM on 14-4-9.Email : nightkid-s@163.com
 */
public interface OnFailSessionObserver {
    public void onFailSession(String errorInfo, int failCode);
}
