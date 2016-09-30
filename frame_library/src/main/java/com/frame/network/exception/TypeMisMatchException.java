package com.frame.network.exception;

/**
 *  网络请求参数类型匹配错误
 * Created by LDM on 14-3-20. Email : nightkid-s@163.com
 */
public class TypeMisMatchException extends Exception{

    public TypeMisMatchException() {
    }

    public TypeMisMatchException(String detailMessage) {
        super(detailMessage);
    }
}
