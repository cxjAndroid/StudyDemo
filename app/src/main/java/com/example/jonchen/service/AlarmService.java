package com.example.jonchen.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.jonchen.receiver.AlarmReceiver;

import java.util.Random;

/**
 * Created by jon on 8/30/17.
 */

public class AlarmService extends Service {

    private final static int GRAY_SERVICE_ID = 1001;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT < 18) {
            startForeground(GRAY_SERVICE_ID, new Notification());//API < 18 ，此方法能有效隐藏Notification上的图标
        } else {
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }

        flags = START_FLAG_REDELIVERY;

        if (intent != null) {
            long notifyTime = intent.getLongExtra("notifyTime", 0);

            // 生成一个随机数对象
            Random r = new Random();
            Intent broadIntent = new Intent(
                    AlarmReceiver.COUPON_ADD_NOTICE_ACTION);
            broadIntent.putExtra("title",
                    String.valueOf(notifyTime));
            PendingIntent pi = PendingIntent.getBroadcast(this, r.nextInt(),
                    broadIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                am.setExact(AlarmManager.RTC_WAKEUP, notifyTime, pi);
            } else {
                am.set(AlarmManager.RTC_WAKEUP, notifyTime, pi);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 给 API >= 18 的平台上用的灰色保活手段
     * API >= 18，同时启动两个id相同的前台Service，然后再将后启动的Service做stop处理
     */
    public static class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }

}
