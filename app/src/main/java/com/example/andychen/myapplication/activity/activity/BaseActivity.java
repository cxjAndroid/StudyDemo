package com.example.andychen.myapplication.activity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andychen on 2016/6/1.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private boolean isBindEventBus;
    private Object subscriber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDate();
    }

    public abstract void initView();

    public abstract void initDate();

    public void registerEventBus(Object subscriber) {
        this.subscriber = subscriber;
        EventBus.getDefault().register(subscriber);
        isBindEventBus = true;
        Toast.makeText(this,"register eventBus",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBindEventBus){
            EventBus.getDefault().unregister(subscriber);
            Toast.makeText(this,"unregister eventBus",Toast.LENGTH_SHORT).show();
        }
    }
}
