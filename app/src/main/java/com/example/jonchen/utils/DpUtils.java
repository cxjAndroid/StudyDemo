package com.example.jonchen.utils;

import android.content.Context;
import android.util.TypedValue;

import com.example.jonchen.base.BaseApplication;

/**
 * Created by chenxujun on 2016/3/25.
 */
public class DpUtils {

    /**
     * dip转换px
     */
    public static int dip2px(Context context, int dip) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return (int) px;
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip
                , BaseApplication.getApplication().getResources().getDisplayMetrics());
        return (int) px;
    }

    /** px转换dip */
    public static int px2dip(int px) {
        final float scale = BaseApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
