package com.duyduong.jobhunter.domain;

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

    public RestResponse(int statusCode, Object message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public static <T> RestResponse<T> success(Object message) {
        return new RestResponse<>(200, message);
    }
    public static <T> RestResponse<T> failed(Object message, T data) {
        return new RestResponse<>(400, message, data);
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
