package com.example.andychen.myapplication.activity.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.andychen.myapplication.R;

import java.io.Serializable;

/**
 * Created by chenxujun on 2015/5/21.
 */
public class IntentUtils {

    /**
     * 开启一个activity
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Activity context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivityAndFinish(Activity context, Intent intent) {
        context.startActivity(intent);
        context.finish();
    }

    /**
     * 开启一个activity,显示从左进入动画
     *
     * @param context
     * @param cls
     */
    public static void startActivityLeftIn(Activity context, Class<? extends Activity> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }

    public static void startActivityLeftIn(Activity context, Class<? extends Activity> cls, int requestCode) {
        Intent intent = new Intent(context, cls);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }


    public static void startActivityUpIn(Activity context, Class<? extends Activity> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_up_in, R.anim.enter);
    }

    public static void startIntentActivityLeftIn(Intent intent, Activity context, Class<? extends Activity> cls) {
        intent.setClass(context, cls);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }


    public static void startActivityLeftIn(Activity context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }

    public static void startActivityUpIn(Activity context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_up_in, R.anim.enter);
    }


    /**
     * 开启一个activity,显示从左进入动画
     *
     * @param context
     * @param cls
     */
    public static void startActivityLeftInAndFinish(Activity context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        context.finish();
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }

    public static void startActivityUpInAndFinish(Activity context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        context.finish();
        context.overridePendingTransition(R.anim.push_up_in, R.anim.enter);
    }


    public static void startActivityLeftInAndFinish(Intent intent, Activity context, Class<?> cls) {
        intent.setClass(context, cls);
        context.startActivity(intent);
        context.finish();
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }


    public static void startActivityLeftIn(Intent intent, Activity context, Class<?> cls) {
        intent.setClass(context, cls);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }

    public static void startActivityLeftInAndFinish(Activity context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        context.finish();
        context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
    }

    /**
     * 开启一个activity
     *
     * @param context
     * @param cls
     * @param delaytime 延迟执行的时间毫秒
     */
    public static void startActivityForDelay(final Activity context, final Class<?> cls, final long delaytime) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delaytime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, cls);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.anim_enter, R.anim.exit_anim_enter);
            }
        }.start();
    }

    /**
     * 开启一个activity
     *
     * @param context
     * @param intent
     * @param delaytime 延迟执行的时间毫秒
     */
    public static void startActivityForDelayAndFinish(final Activity context, final Intent intent, final long delaytime) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delaytime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                context.startActivity(intent);
                context.finish();
            }
        }.start();
    }


    /**
     * 切换界面
     *
     * @param a
     * @param descClass
     */
    /*public static void switchActivity(BaseActivity a, Class<? extends  Activity> descClass,
                                      Bundle bundle) {
        switchActivity(a, descClass, bundle, 0);
    }*/


 /*   *//**
     * 切换界面
     *
     * @param a
     * @param descClass
     * @param requestCode :请求码
     *//*
    public static void switchActivity(BaseActivity a, Class<? extends Activity> descClass,
                                      Bundle bundle, int requestCode) {
        Class<?> mClass = a.getClass();
        if (mClass == descClass) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(a, descClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            a.startActivityForResult(intent, requestCode);
            a.activityLeftIn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    *//**
     * 切换界面
     *
     * @param a
     * @param descClass
     * @param requestCode :请求码
     *//*
    public static void switchActivity(Fragment fragment, Class<? extends Activity> descClass,
                                      Bundle bundle, int requestCode) {
        BaseActivity a = (BaseActivity) fragment.getActivity();
        Class<?> mClass = a.getClass();
        if (mClass == descClass) {
            return;
        }
        try {
            Intent intent = new Intent(fragment.getActivity(),descClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            fragment.startActivityForResult(intent, requestCode);
            a.activityLeftIn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    /**
     * 给Activity设置结果返回  只返回一个字符串
     *
     * @param a          当前所在Activity
     * @param resultCode 返回码
     * @param extraKey   返回的KEY
     * @param extraValue 返回的Value
     */
    public static void setSimpleStringResult(Activity a, int resultCode, String extraKey, String extraValue) {
        Intent intent = new Intent();
        intent.putExtra(extraKey, extraValue);
        a.setResult(resultCode, intent);
    }


    /**
     * 给Activity设置结果返回  只返回一个Serializable对象
     *
     * @param a          当前所在Activity
     * @param resultCode 返回码
     * @param extraKey   返回的KEY
     * @param extraObj   返回的Serializable对象
     */
    public static void setSimpleSerializableResult(Activity a, int resultCode, String extraKey, Serializable extraObj) {
        Intent intent = new Intent();
        intent.putExtra(extraKey, extraObj);
        a.setResult(resultCode, intent);
    }


    /**
     * 给Activity设置结果返回  返回Bundle对象
     *
     * @param a          当前所在Activity
     * @param resultCode 结果码
     * @param bundle     返回的bundle对象
     */
    public static void setBundleResult(Activity a, int resultCode, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle == null ? new Bundle() : bundle);
        a.setResult(resultCode, intent);
    }

    /**
     * 激活系统图库，选择一张图片
     *
     * @param a           当前activity
     * @param requestCode 请求码
     */
    public static void startPickPhotoIntent(Activity a, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        a.startActivityForResult(intent, requestCode);
    }


    /**
     * 将电话号码带到拨号盘
     *
     * @param number 拨打的电话号码
     */
    public static void call(Activity a, String number) {
        Uri uri = Uri.parse("tel:" + number);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
        a.startActivity(callIntent);
    }


}
