package com.example.jonchen.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jonchen.base.BaseApplication;


/**
 * Created by chenxujun on 2015/8/10.
 *  缓存类,采用SharedPreferences方式存储和获取.
 */
public class CacheUtils {
    private static String CACHE_FILE_NAME = "km_config";
    private static SharedPreferences mSharedPreferences;
    public static String DOWNLOAD_TIMESTAMP = "DOWNLOAD_TIMESTAMP";
    public static String WATCH_VOLUME = "WATCH_VOLUME";

    /**
     * @param context
     * @param key      要取的数据的键
     * @param defValue 缺省值
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getBoolean(key, defValue);
    }


    public static boolean getBoolean(Context context, String cacheFileName , String key, boolean defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(cacheFileName, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 存储一个boolean类型数据
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        if(mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static void putBoolean(Context context, String cacheFileName, String key, boolean value) {
        if(mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(cacheFileName, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }


    public static void putString(Context context, String key, String value){
        if(mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putString(key, value).apply();

    }

    public static void putString(String key, String value){
        if(mSharedPreferences == null) {
            mSharedPreferences = BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putString(key, value).apply();

    }

    public static void putLong(String key, long value){
        if(mSharedPreferences == null) {
            mSharedPreferences = BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    public static long getLong(String key, long defValue){
        if(mSharedPreferences == null) {
            mSharedPreferences = BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getLong(key, defValue);
    }

    public static String getString(Context context, String key, String defValue){
        if(mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, defValue);
    }

    public static String getString(String key, String defValue){
        if(mSharedPreferences == null) {
            mSharedPreferences = BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, defValue);
    }

    public static void clearCache(Context context){
        mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        mSharedPreferences.edit().clear().apply();
    }

}
