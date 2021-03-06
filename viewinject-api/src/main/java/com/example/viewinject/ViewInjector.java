package com.example.viewinject;

import android.app.Activity;
import android.view.View;


public class ViewInjector {
    private static final String SUFFIX = "$$ViewInject";

    public static void bind(Activity activity) {
        ViewInject proxyActivity = findProxyActivity(activity);
        proxyActivity.bind(activity, activity);
    }

    public static void bind(Object object, View view) {
        ViewInject proxyActivity = findProxyActivity(object);
        proxyActivity.bind(object, view);
    }

    private static ViewInject findProxyActivity(Object activity) {
        try {
            Class clazz = activity.getClass();
            Class injectorClazz = Class.forName(clazz.getName() + SUFFIX);
            return (ViewInject) injectorClazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(String.format("can not find %s , something when compiler.", activity.getClass().getSimpleName() + SUFFIX));
    }
}
