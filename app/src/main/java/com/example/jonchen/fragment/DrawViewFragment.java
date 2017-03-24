package com.example.jonchen.fragment;

import android.view.MotionEvent;
import android.view.View;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.view.MyView;

import butterknife.BindView;

/**
 * Created by andychen on 2016/10/12.
 */

public class DrawViewFragment extends BaseFragment {

    @BindView(R.id.myView)
    MyView myView;
    private int downX;
    private int downY;
    private int moveX;
    private int moveY;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_view;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void initData() {

        //myView.setClickable(true);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = (int) event.getX();
                        downY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX = (int) event.getX();
                        moveY = (int) event.getY();
                        myView.setAction(downX, downY, moveX, moveY);
                        downX = moveX;
                        downY = moveY;
                        break;
                }
                downX = (int) event.getX();
                downY = (int) event.getY();
                moveX = (int) event.getX();
                moveY = (int) event.getY();
                myView.setAction(downX, downY, moveX, moveY);
                downX = moveX;
                downY = moveY;
                /**
                 *此时return true会跳过view的onTouchEvent方法，即view的dispatchTouchEvent返回true，上层
                 *viewGroup继续分发事件。若返回false会进入onTouchEvent方法，view默认不可点击，会使得view的
                 *onTouchEvent和dispatchTouchEvent方法返回false，导致viewGroup的mFirstTouchTarget值为
                 *null，无法进入后续事件分发。
                 */
                return true;
            }
        });
    }


}
