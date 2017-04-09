package com.example.jonchen.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.example.jonchen.utils.ToastUtils;

/**
 * Created by jon on 3/31/17.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyIBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public void showToast() {
        ToastUtils.show("service method");
    }


    public class MyIBinder extends Binder {

        public void show() {
            showToast();
        }

    }

}
