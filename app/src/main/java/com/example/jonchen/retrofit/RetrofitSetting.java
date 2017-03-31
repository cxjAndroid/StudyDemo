package com.example.jonchen.retrofit;

import com.example.jonchen.utils.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jon on 3/31/17.
 */

public class RetrofitSetting {

    public static final String HK_BASE_URL = "https://patientapi.hk515.com/";
    //public static final String BASE_URL = "http://120.25.225.5:8090/kmhc-modem-restful/services/";
    //public static final String BASE_URL = NetUrl.URL + "/kmhc-modem-restful/services/";
    //public static final String KM_BASE_URL = "http://120.25.225.5:8090/kmhc-modem-restful/services/";
    public static final String KM_BASE_URL = "http://watch.medquotient.com:8880/kmhc-modem-restful/services/";//正式环境
    public static final String ZH_BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String JKY_URL = "http://218.17.23.74:8089/jkyh_app/";


    public static Retrofit buildRetrofit() {
        return buildRetrofit(KM_BASE_URL);
    }

    public static Retrofit buildRetrofit(String url) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                .create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpUtils.getHttpClient())
                .build();
    }
}
