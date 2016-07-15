package com.example.andychen.myapplication.activity.retrofit;


import com.example.andychen.myapplication.activity.security.Des3;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.StringUtils;

import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andychen on 2016/7/14.
 */
public abstract class ResponseCallback implements Callback<ResponseBody> {

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        JSONObject jsonObject = null;
        int resultCode = -1;
        try {
            HttpUrl url = response.raw().request().url();
            LogUtils.e(url.toString());

            String decode = Des3.decode(response.body().string());
            if (!StringUtils.isEmpty(decode)) {
                jsonObject = new JSONObject(decode);
                resultCode = jsonObject.optInt("resultCode");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultCode == 1) {
                onSuccessResponse(call, jsonObject);
            } else {
                onFailureResponse(call, jsonObject);
            }
        }
    }

    @Override
    public  void onFailure(Call<ResponseBody> call, Throwable t){
        String message = t.getMessage();
        onFailureNoResponse(call,t);
    }

    public abstract void onSuccessResponse(Call<ResponseBody> call, JSONObject response);

    public abstract void onFailureResponse(Call<ResponseBody> call, JSONObject response);

    public abstract void onFailureNoResponse(Call<ResponseBody> call, Throwable response);

}
