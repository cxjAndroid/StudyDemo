package com.example.andychen.myapplication.activity.mvp_presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.activity.SecondActivity;
import com.example.andychen.myapplication.activity.activity.ThirdActivity;
import com.example.andychen.myapplication.activity.bean.User;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.retrofit.ApiInterface;
import com.example.andychen.myapplication.activity.retrofit.RequestParams;
import com.example.andychen.myapplication.activity.retrofit.ResponseCallback;
import com.example.andychen.myapplication.activity.security.Des3;
import com.example.andychen.myapplication.activity.utils.IntentUtils;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by andychen on 2016/7/1.
 */
public class MainPresenter extends BasePresenter {

    private Context context;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void performOnClick(View v) {
        super.performOnClick(v);
        switch (v.getId()) {
            case R.id.btn:
                IntentUtils.startActivityLeftIn((Activity) context, SecondActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                IntentUtils.startActivityLeftIn((Activity) context, ThirdActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
                break;
            case R.id.iv:
               /* Activity activity = (Activity) this.context;
                activity.startActivityForResult(new Intent(activity,CaptureActivity.class),0);*/
               /* Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").build();
                ApiInterface repo = retrofit.create(ApiInterface.class);
                Call<ResponseBody> call = repo.getResponse("square", "retrofit");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String body = response.body().string();
                            String s = response.body().toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        LogUtils.e(call.toString());
                        LogUtils.e(t.toString());
                    }
                });*/

                /*Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.25.225.5:8090/").build();
                retrofit.create(ApiInterface.class).getUpdateMessage("kmhc-apk-service/apk/verCheck").enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String body = response.body().string();
                            String s = response.body().toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        String message = t.getMessage();

                    }
                });*/

                RequestParams params = new RequestParams();
                params.put("mobilePhone", "C840104238F");
                params.put("password", "C840104238F");
                params.put("phoneOsType", "A");
                params.put("pushInfo", "");
                params.put("userType", "1");
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://218.17.23.74:8089/jkyh_app/").build();

                retrofit.create(ApiInterface.class).Login(params).enqueue(new ResponseCallback() {
                    @Override
                    public void onFailureNoResponse(Call<ResponseBody> call, Throwable t) {

                    }

                    @Override
                    public void onSuccessResponse(Call<ResponseBody> call, JSONObject response) {
                        JSONObject data = response.optJSONObject("resultData");
                    }

                    @Override
                    public void onFailureResponse(Call<ResponseBody> call, JSONObject response) {

                    }
                });
                break;
        }
    }
}
