package com.example.jonchen.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.jonchen.utils.LogUtils;

/**
 * Created by jon on 3/18/17.
 */

public class MyBtn extends AppCompatButton {
    public MyBtn(Context context) {
        super(context);
    }

    public MyBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("onTouchEvent----"+event.getAction());
        return super.onTouchEvent(event);
    }
}
