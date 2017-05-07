package com.example.jonchen.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.jonchen.utils.LogUtils;

/**
 * @author chenxujun
 */
public class MyViewPager extends ViewPager {

    /* 是否可以滑动 */
    private boolean isScrollable = true;
    private float downX;
    private float moveX;


    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isScrollable) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    LogUtils.e("downX-----" + downX);
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveX = event.getX();
                    LogUtils.e("moveX-----" + moveX);
                   
                    if (moveX - downX > 0 && getCurrentItem() == 0) {
                        LogUtils.e("return false");
                        return false;

                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            LogUtils.e(" super.onInterceptTouchEvent(event)");
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isScrollable) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }

}
