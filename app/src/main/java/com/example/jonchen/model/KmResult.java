package com.example.jonchen.model;

/**
 * Created by chenxujun on 16-10-28.
 */

public class KmResult<T> {
    private int errorCode;
    private String msg;
    private Content<T> content;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Content<T> getContent() {
        return content;
    }

    public void setContent(Content<T> content) {
        this.content = content;
    }

    public class Content<T> {
        private T list;

        public T getList() {
            return list;
        }

        public void setList(T list) {
            this.list = list;
        }
    }

}

