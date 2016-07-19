package com.example.andychen.myapplication.activity.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author chenxujun
 */
public class MyViewPager extends ViewPager {

	/* 是否可以滑动 */
	private boolean isScrollable = false;

	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (isScrollable) {
			return super.onInterceptTouchEvent(arg0);
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (isScrollable) {
			return super.onTouchEvent(arg0);
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
