package com.kim.cas.client.starter.entity;

/**
 * @author huangjie
 * @description  响应结果对象
 * @date 2020/5/20
 */
public class Result<T> {


    private Long timestamp;
    private String message;
    private T data;
    private String path;


    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
