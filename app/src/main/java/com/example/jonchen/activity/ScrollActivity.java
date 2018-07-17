package com.example.jonchen.activity;

import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;

import butterknife.BindView;

/**
 * @author jon
 * @since 3/22/18
 */

public class ScrollActivity extends BaseActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.image5)
    ImageView image5;
    @BindView(R.id.image6)
    ImageView image6;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_scroll;
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void initData() {

    }
}
