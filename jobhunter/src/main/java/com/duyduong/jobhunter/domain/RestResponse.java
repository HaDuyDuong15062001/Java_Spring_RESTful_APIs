package com.duyduong.jobhunter.domain;

import lombok.Builder;

@Builder
public class RestResponse<T>{
    private int statusCode;
    private String error;
    private Object message; // maybe String or Array
    private T data;

    public RestResponse(Object message) {
        this.message = message;
    }

    public RestResponse() {
    }

    public RestResponse(int statusCode, Object message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public static <T> RestResponse<T> success(Object message) {
        return new RestResponse<>(200, message);
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
