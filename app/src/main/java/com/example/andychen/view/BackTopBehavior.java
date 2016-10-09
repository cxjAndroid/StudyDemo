package com.example.andychen.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;

import com.example.andychen.utils.AnimatorUtil;

/**
 * Created by chenxujun on 16-9-23.
 */

public class BackTopBehavior extends FloatingActionButton.Behavior {
    private boolean isAnimatingOut = false;

    public BackTopBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
       /*         System.out.println("onNestedScroll- dxConsumed:" + dxConsumed + "; dyConsumed: " + dyConsumed + "; dxUnconsumed: " + dxUnconsumed + "; dyUnconsumed: " + dyUnconsumed);

        if (dyConsumed > 0 && dyUnconsumed == 0) {
            System.out.println("上滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            System.out.println("到边界了还在上滑。。。");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            System.out.println("下滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0) {
            System.out.println("到边界了，还在下滑。。。");
        }*/

        if (((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed > 0))) {// 显示
            AnimatorUtil.scaleShow(child, null);
        } else if (((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed < 0))
                && !isAnimatingOut) {
            AnimatorUtil.scaleHide(child, viewPropertyAnimatorListener);
        }
    }

    private ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {

        @Override
        public void onAnimationStart(View view) {
            isAnimatingOut = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimatingOut = false;
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View arg0) {
            isAnimatingOut = false;
        }
    };

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }
}
