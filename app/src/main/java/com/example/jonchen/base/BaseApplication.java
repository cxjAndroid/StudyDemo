package com.example.jonchen.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by chenxujun on 2016/6/20.
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    /** 主线程ID */
    private static int mMainThreadId = -1;
    /** 主线程ID */
    private static Thread mMainThread;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;
    /** 主线程Looper */
    private static Looper mMainLooper;


    @Override
    public void onCreate() {
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
        super.onCreate();

        LeakCanary.install(this);
        Fresco.initialize(this);

    }

    public static BaseApplication getApplication() {
        return mInstance;
    }

    /** 获取主线程ID */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /** 获取主线程 */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /** 获取主线程的handler */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /** 获取主线程的looper */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
