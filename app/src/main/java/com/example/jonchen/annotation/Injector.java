package com.example.jonchen.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/1/7.
 */

public class Injector {

    private static InjectViewProcess process = new InjectViewProcess();

    public static void inject(Activity activity) {
        inject(activity, activity.getWindow().getDecorView());
    }

    public static void inject(Object obj, View rootView) {
        final Class<?> aClass = obj.getClass();
        final Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f : declaredFields) {
            if (process.accept(f)) {
                process.process(obj, rootView, f);
            }
        }
    }

}
