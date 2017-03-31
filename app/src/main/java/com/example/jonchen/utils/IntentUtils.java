package com.example.jonchen.utils;
import android.content.Intent;
import android.support.annotation.AnimRes;
import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;

/**
 * Created by chenxujun on 2015/5/21.
 */
public class IntentUtils {

    public static BaseActivity startActivity(BaseActivity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        setSwitchAnim(activity);
        return activity;
    }

    public static BaseActivity startActivityForResult(BaseActivity activity, Class<?> cls, int requestCode) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, requestCode);
        setSwitchAnim(activity);
        return activity;
    }


    public static BaseActivity startActivityWithAnim(BaseActivity activity, Class<?> cls, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        setSwitchAnim(activity, enterAnim, exitAnim);
        return activity;
    }

    public static BaseActivity finishActivity(BaseActivity activity) {
        activity.finish();
        setSwitchAnim(activity, R.anim.anim_pop_enter, R.anim.anim_pop_exit);
        return activity;
    }

    public static BaseActivity finishActivityWithAnim(BaseActivity activity, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        activity.finish();
        setSwitchAnim(activity, enterAnim, exitAnim);
        return activity;
    }


    private static void setSwitchAnim(BaseActivity activity) {
        activity.overridePendingTransition(R.anim.anim_pull_enter, R.anim.anim_pull_exit);
    }

    private static void setSwitchAnim(BaseActivity activity, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        activity.overridePendingTransition(enterAnim, exitAnim);
    }

}
