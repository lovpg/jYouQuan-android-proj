package com.lbsm.kdang.net;

/**
 * Created by zrf on 2016/10/16.
 */

public class Result<T> {
    private int statusCode;
    private String message;
    private T body;

    public Result(int statusCode, String message,  T body){
        this.statusCode = statusCode;
        this.message=message;
        this.body=body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
