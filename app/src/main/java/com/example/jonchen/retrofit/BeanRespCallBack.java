package com.example.jonchen.retrofit;


import com.example.jonchen.model.entity.Result;
import com.example.jonchen.utils.LogUtils;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxujun on 2016/7/14.
 */
public abstract class BeanRespCallBack<T> implements Callback<Result<T>> {

    private T data;
    private Result<T> body;

    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {

        int resultCode = -1;

        try {
            HttpUrl url = response.raw().request().url();
            LogUtils.e(url.toString());

            body = response.body();
            data = body.getData();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (data instanceof ResponseBody) {
                ((ResponseBody) data).close();
            }
            if (body.isSuccess()) {
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

    public void onFailureResp(T response) {

    }

    public void onFail(Throwable response) {

    }

}
