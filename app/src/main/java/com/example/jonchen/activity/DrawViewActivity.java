package com.example.jonchen.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.R;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.MyView;

import butterknife.BindView;

/**
 * Created by andychen on 2016/10/12.
 */

public class DrawViewActivity extends BaseActivity {

    @BindView(R.id.myView)
    MyView myView;
    private int startX;
    private int startY;
    private int moveX;
    private int moveY;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_view;
    }

    @Override
    protected void initView() {
       /* float myViewTranslationY = myView.getTranslationY();
        float translationY = rlShow.getTranslationY();
        LogUtils.e(String.valueOf(myViewTranslationY)+"----------"+String.valueOf(translationY));
        ObjectAnimator animator = ObjectAnimator.ofFloat(rlShow, "translationY", -rlShow.getHeight(), translationY);
        animator.setDuration(1000);
        animator.start();*/
    }

    @Override
    public void initDate() {
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
              /*  switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX = (int) event.getX();
                        moveY = (int) event.getY();
                        myView.setAction(startX, startY, moveX, moveY);
                        startX = moveX;
                        startY = moveY;
                        break;
                }*/
                startX = (int) event.getX();
                startY = (int) event.getY();
                moveX = (int) event.getX();
                moveY = (int) event.getY();
                myView.setAction(startX, startY, moveX, moveY);
                startX = moveX;
                startY = moveY;
                return true;
            }
        });
    }


}
