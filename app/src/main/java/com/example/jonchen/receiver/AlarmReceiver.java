package com.example.jonchen.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.example.jonchen.R;
import com.example.jonchen.activity.ActionActivity;

import java.util.List;


/**
 * @Description 领券中心 广播接收器
 * @Author 16080537
 * @Date 2017/4/24
 */
public class AlarmReceiver extends BroadcastReceiver {

    public static final String COUPON_ADD_NOTICE_ACTION = "coupon_center_add_notice";
    public static final String COUPON_NOTI_JUMP_ACTION = "coupon_center_notice_jump";
    public final static int NOTIFICATION_ID = "baseapi_add_notice".hashCode();
    private static String saveId = " ";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (COUPON_ADD_NOTICE_ACTION.equals(action)) {
            String title = intent.getStringExtra("title");
           /*  if (saveId.equals(title)) {
                return;
            }
            saveId = title;*/
            //            String content = intent.getStringExtra("content");
            //            String targetUrl = intent.getStringExtra("targetUrl");
            Bitmap bigIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_location_on_white_24dp);

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle(title)
                    /*.setContentText(content)*/
                    .setSmallIcon(R.drawable.ic_location_on_white_24dp)
                    .setLargeIcon(bigIcon)
                    .setAutoCancel(true);

            Intent intentCross = new Intent(context, ActionActivity.class);
            intentCross.setAction(AlarmReceiver.COUPON_NOTI_JUMP_ACTION);
            PendingIntent pi = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intentCross, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(pi);
            Notification nf = builder.build();

            nm.notify(intent.hashCode(), nf);
        }
    }

}
