package com.example.andychen.myapplication.activity.retrofit;


import com.example.andychen.myapplication.activity.utils.LogUtils;

import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxujun on 2016/7/14.
 */
public abstract class RespCallback implements Callback<ResponseBody> {

    private ResponseBody responseBody;
    private String returnData;
    private String responseString;
    private boolean isSuccess;

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        JSONObject jsonObject = null;
        int resultCode = -1;
        try {
            HttpUrl url = response.raw().request().url();
            LogUtils.e(url.toString());

            responseBody = response.body();
            responseString = responseBody.string();

            LogUtils.e(responseString);

            JSONObject object = new JSONObject(responseString);
            isSuccess = object.optBoolean("IsSuccess");
            returnData = object.optString("ReturnData");

           /* Gson gson = new Gson();
            Doctor[] doctors = gson.fromJson(returnData, Doctor[].class);
            int length = doctors.length;
            LogUtils.e(returnData);

            List<Doctor> docList = gson.fromJson(returnData, new TypeToken<List<Doctor>>() {
            }.getType());
            int size = docList.size();

            Result<List<Doctor>> res = gson.fromJson(responseString, new TypeToken<Result<List<Doctor>>>() {
            }.getType());


            List<Doctor> doctorList = res.getData();
            doctorList.size();*/

           /* String decode = Des3.decode(response.body().string());
            if (!StringUtils.isEmpty(decode)) {
                jsonObject = new JSONObject(decode);
                resultCode = jsonObject.optInt("resultCode");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isSuccess) {
                onSuccessResp(responseString, returnData);
            } else {
                onFailureResp(responseString, returnData);
            }
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }


    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        onFail(t);
    }

    public abstract void onSuccessResp(String responseString, String data);

    public abstract void onFailureResp(String responseString, String data);

    public abstract void onFail(Throwable t);

}
