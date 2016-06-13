package com.example.andychen.myapplication.activity.activity;

import android.widget.Toast;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.event.EventMessage;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andychen on 2016/6/1.
 */
public class SecondActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initDate() {
        registerEventBus(this);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_second)
    void click() {
        Toast.makeText(this, "second", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage msg){
        Toast.makeText(this, (CharSequence) msg.getMessage(),Toast.LENGTH_SHORT).show();
    }

}
