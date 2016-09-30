package com.frame.network.exception;

/**
 * 会话失败，失败原因可能为 请求失败或者解析失败，以failCode区分异常类型
 * Created by LDM on 14-4-8. Email : nightkid-s@163.com
 */
public class FailSessionException extends Exception{

    private int failCode;

    public FailSessionException(String detailMessage, int failCode) {
        super(detailMessage);
        this.failCode = failCode;
    }

    public int getFailCode() {
        return failCode;
    }
}
