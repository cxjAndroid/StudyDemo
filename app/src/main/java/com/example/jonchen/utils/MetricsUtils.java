package com.example.jonchen.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewConfiguration;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by chenxujun on 2016/6/24.
 */
public class MetricsUtils {
    public static int loadingPageHeight;

    public static int[] getPixels() {
        DisplayMetrics displayMetrics = BaseApplication.getApplication().
                getApplicationContext().getResources().getDisplayMetrics();

        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        if (hasNavBar()) {
            displayHeight = displayHeight + getNavigationBarHeight();
        }
        return new int[]{displayWidth, displayHeight};
    }


    public static int getStatusPageHeight(Context context) {
        if (loadingPageHeight != 0) {
            return loadingPageHeight;
        }
        int[] pixels = getPixels();
        int statusBarHeight = getStatusBarHeight();
        int barHeight = getNavigationBarHeight();
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            int[] textSizeAttr = new int[]{R.attr.actionBarSize};
            TypedArray a = context.obtainStyledAttributes(new TypedValue().data, textSizeAttr);
            actionBarHeight = (int) a.getDimension(0, -1);
            a.recycle();
        }

        loadingPageHeight = pixels[1] - statusBarHeight - barHeight - actionBarHeight;
        return loadingPageHeight;
    }

    public static void getDensity() {
        float xdpi = BaseApplication.getApplication().getResources().getDisplayMetrics().xdpi;
        float ydpi = BaseApplication.getApplication().getResources().getDisplayMetrics().ydpi;
        float density = BaseApplication.getApplication().getResources().getDisplayMetrics().density;
        float densityDpi = BaseApplication.getApplication().getResources().getDisplayMetrics().densityDpi;
    }


    public void getViewConfiguration() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(BaseApplication.getApplication());
        int touchSlop = viewConfiguration.getScaledTouchSlop();
        //获取Fling速度的最小值和最大值
        int minimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        int maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        //判断是否有物理按键
        boolean isHavePermanentMenuKey = viewConfiguration.hasPermanentMenuKey();
        LogUtils.e("touchSlop:" + String.valueOf(touchSlop));
        LogUtils.e("minimumVelocity:" + String.valueOf(minimumVelocity));
        LogUtils.e("maximumVelocity:" + String.valueOf(maximumVelocity));
        LogUtils.e("isHavePermanentMenuKey:" + isHavePermanentMenuKey);
    }


    public static int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = BaseApplication.getApplication().getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
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
