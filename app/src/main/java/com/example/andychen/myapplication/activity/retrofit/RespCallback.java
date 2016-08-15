package com.example.andychen.myapplication.activity.retrofit;


import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.bean.Result;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andychen on 2016/7/14.
 */
public abstract class RespCallback implements Callback<ResponseBody> {

    private ResponseBody responseBody;

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        JSONObject jsonObject = null;
        int resultCode = -1;

        try {
            HttpUrl url = response.raw().request().url();
            LogUtils.e(url.toString());

            responseBody = response.body();
            String responseString = responseBody.string();
            LogUtils.e(responseString);

            JSONObject object = new JSONObject(responseString);
            String returnData = object.optString("ReturnData");

            Gson gson = new Gson();
            Doctor[] doctors = gson.fromJson(returnData, Doctor[].class);
            int length = doctors.length;
            LogUtils.e(returnData);

            List<Doctor> docList = gson.fromJson(returnData, new TypeToken<List<Doctor>>() {
            }.getType());
            int size = docList.size();

            Result<List<Doctor>> res = gson.fromJson(responseString, new TypeToken<Result<List<Doctor>>>() {
            }.getType());


            List<Doctor> doctorList = res.getData();
            doctorList.size();

           /* String decode = Des3.decode(response.body().string());
            if (!StringUtils.isEmpty(decode)) {
                jsonObject = new JSONObject(decode);
                resultCode = jsonObject.optInt("resultCode");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            responseBody.close();
            if (resultCode == 1) {
                onSuccessResp(call, jsonObject);
            } else {
                onFailureResp(call, jsonObject);
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        String message = t.getMessage();
        onFail(call, t);
    }


    public abstract void onSuccessResp(Call<?> call, JSONObject response);

    public abstract void onFailureResp(Call<?> call, JSONObject response);

    public abstract void onFail(Call<?> call, Throwable response);

}
