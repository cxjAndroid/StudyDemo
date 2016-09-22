package com.example.andychen.myapplication.activity.retrofit;

import com.example.andychen.myapplication.activity.security.Des3;

import java.util.HashMap;

/**
 * Created by chenxujun on 2016/7/13.
 */
public class RequestParams extends HashMap {
    @Override
    public Object put(Object key, Object value) {
        value = Des3.encode(value.toString());
        return super.put(key, value);
    }
}
