package com.example.jonchen.retrofit;

import com.example.jonchen.utils.LogUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by chenxujun on 2016/8/9.
 */
public class OkHttpUtils {

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(30, TimeUnit.SECONDS)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request()
                                            .newBuilder()
                                            .cacheControl(CacheControl.FORCE_NETWORK)
                                            .build();

                                    Response response = chain.proceed(request);
                                    int code = response.code();
                                    LogUtils.e(request.url().toString()+"------responseCode:"+code);
                                   /* Headers headers = response.headers();
                                    Set<String> responseHeadNames = headers.names();
                                    for(String name : responseHeadNames){
                                        List<String> values = headers.values(name);
                                        for(String value : values){
                                        }
                                    }*/

                                    return response;
                                }
                            }).build();
                }
            }
        }
        return okHttpClient;
    }
}
