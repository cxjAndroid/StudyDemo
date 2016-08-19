package com.example.andychen.myapplication.activity.retrofit;


import com.example.andychen.myapplication.activity.bean.Result;
import com.example.andychen.myapplication.activity.utils.LogUtils;

import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andychen on 2016/7/14.
 */
public abstract class BeanRespCallBack<T> implements Callback<Result<T>> {

    private T data;

    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {

        int resultCode = -1;

        try {
            HttpUrl url = response.raw().request().url();
            LogUtils.e(url.toString());

            Result<T> body = response.body();
            data = body.getData();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (data instanceof ResponseBody) {
                ((ResponseBody) data).close();
            }
            if (true) {
                onSuccessResp(data);
            } else {
                onFailureResp(data);
            }
        }
    }

    @Override
    public void onFailure(Call<Result<T>> call, Throwable t) {
        String message = t.getMessage();
        onFail(t);
    }


    public abstract void onSuccessResp(T response);

    public abstract void onFailureResp(T response);

    public abstract void onFail(Throwable response);

}
