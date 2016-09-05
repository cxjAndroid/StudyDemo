package com.example.andychen.myapplication.activity.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;

import com.example.andychen.myapplication.activity.base.BaseApplication;

import java.lang.reflect.Method;

/**
 * Created by andychen on 2016/6/24.
 */
public class MetricsUtils {

    public static int[] getPixels() {
        DisplayMetrics displayMetrics = BaseApplication.getApplication().
                getApplicationContext().getResources().getDisplayMetrics();

        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        if (hasNavBar()) {
            displayHeight = displayHeight + getNavigationBarHeight();
        }
        return new int[]{displayHeight, displayWidth};
    }

    public static void getDensity() {
        float xdpi = BaseApplication.getApplication().getResources().getDisplayMetrics().xdpi;
        float ydpi = BaseApplication.getApplication().getResources().getDisplayMetrics().ydpi;
        float density = BaseApplication.getApplication().getResources().getDisplayMetrics().density;
        float densityDpi = BaseApplication.getApplication().getResources().getDisplayMetrics().densityDpi;
    }


    /**
     * 检查是否存在虚拟按键栏
     *
     * @return
     */
    private static boolean hasNavBar() {
        Resources res = BaseApplication.getApplication().getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(BaseApplication.getApplication()).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    /**
     * 获取虚拟按键栏高度
     *
     * @return
     */
    public static int getNavigationBarHeight() {
        int result = 0;
        if (hasNavBar()) {
            Resources res = BaseApplication.getApplication().getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }
}
