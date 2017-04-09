package com.example.jonchen.activity;

import android.view.MotionEvent;
import android.view.View;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.MyBtn;
import com.example.jonchen.view.MyLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by jon on 3/18/17.
 */

public class ActionActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    @BindView(R.id.mLayout)
    MyLayout mLayout;

    @BindView(R.id.mBtn)
    MyBtn mBtn;
    private Thread thread;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_action;
    }

    @Override
    protected void initView() {
    }



    @Override
    public void initData() {
        mLayout.setOnTouchListener(this);
        mBtn.setOnTouchListener(this);
        mLayout.setOnClickListener(this);
        mBtn.setOnClickListener(this);

        EventBus.getDefault().post(new EventMessage<>("hahaha"));

        /*   while(true){

           }*/

      /*  thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while(!thread.isInterrupted()){
                    LogUtils.e("no interrupt");
                }
                LogUtils.e("interrupt");
                *//*try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    LogUtils.e("interrupt");
                }*//*
            }
        });
        thread.start();*/


    }


    @Override
    protected void onDestroy() {
        thread.interrupt();
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {
        LogUtils.e("onClick----"+v.getId());
    }
}
