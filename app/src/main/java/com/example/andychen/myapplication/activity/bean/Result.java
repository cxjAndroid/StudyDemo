package com.example.andychen.myapplication.activity.bean;

/**
 * Created by chenxujun on 2016/8/12.
 */
public class Result<T> {

    private String ReturnMessage;
    private boolean IsSuccess;
    private T ReturnData;

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(String ReturnMessage) {
        this.ReturnMessage = ReturnMessage;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public T getData() {
        return ReturnData;
    }

    public void getData(T returnData) {
        ReturnData = returnData;
    }
}
