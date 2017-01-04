package com.example.jonchen.retrofit;

/**
 * Created by chenxujun on 2016/8/22.
 */
public class ApiException extends RuntimeException {
    private int errorCode;
    public static final int NO_SUCCESS = 110;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
