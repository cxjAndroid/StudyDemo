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
                    /**
                     * 其实这里的getCurrentItem() == 0判断可以不加，因为在ACTION_DOWN时如果可以拖动，mIsBeingDragged
                     * 值为true，会导致super.onInterceptTouchEvent方法返回true，即拦截事件，
                     * 导致mFirstTouchTarget值为null，后续ACTION_MOVE,ACTION_DOWN等事件都无法进入onInterceptTouchEvent
                     * 方法中，自然不会进入条件判断语句中return false，而如果viewpager处于第一页，是无法向右滑动的，所以在向右滑动时，
                     * mIsBeingDragged值会被赋为false，即super.onInterceptTouchEvent方法返回false,不拦截事件，这样
                     * 才会进入ACTION_MOVE这里的判断语句中，这时return false；阻止super.onInterceptTouchEvent(event)方法，
                     * 让其不拦截子view的事件。
                     */
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
