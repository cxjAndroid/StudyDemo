package com.example.andychen.activity;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.andychen.base.BaseActivity;
import com.example.andychen.myapplication.R;
import com.example.andychen.utils.LogUtils;
import com.example.andychen.view.MyView;

import butterknife.BindView;

/**
 * Created by andychen on 2016/10/12.
 */

public class DrawViewActivity extends BaseActivity {

    @BindView(R.id.rlShow)
    RelativeLayout rlShow;
    @BindView(R.id.myView)
    MyView myView;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_view;
    }

    @Override
    protected void initView() {
        rlShow.setVisibility(View.VISIBLE);
       /* float myViewTranslationY = myView.getTranslationY();
        float translationY = rlShow.getTranslationY();
        LogUtils.e(String.valueOf(myViewTranslationY)+"----------"+String.valueOf(translationY));
        ObjectAnimator animator = ObjectAnimator.ofFloat(rlShow, "translationY", -rlShow.getHeight(), translationY);
        animator.setDuration(1000);
        animator.start();*/
    }

    @Override
    public void initDate() {

    }
}
