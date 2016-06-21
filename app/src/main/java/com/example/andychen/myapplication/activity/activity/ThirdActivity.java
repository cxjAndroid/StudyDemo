package com.example.andychen.myapplication.activity.activity;

import android.widget.Toast;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.event.EventMessage;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by chenxujun on 2016/6/12.
 */
public class ThirdActivity extends BaseActivity {
    @Override
    public void initView() {
        setContentView(R.layout.activity_third);

    }

    @Override
    public void initDate() {
        registerEventBus();
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage<String> message){
        String s = message.getMessage();
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
