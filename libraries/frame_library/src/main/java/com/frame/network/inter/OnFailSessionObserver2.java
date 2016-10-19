package com.frame.network.inter;

/**
 * 会话失败的监听器, 带额外反馈信息
 * T 请求的数据对象
 * Created by LDM on 14-4-9.Email : nightkid-s@163.com
 */
public interface OnFailSessionObserver2 {
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData);
}
