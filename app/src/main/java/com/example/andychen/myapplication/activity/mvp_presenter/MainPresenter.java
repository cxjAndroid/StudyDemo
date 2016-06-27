package com.example.andychen.myapplication.activity.mvp_presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.activity.SecondActivity;
import com.example.andychen.myapplication.activity.activity.ThirdActivity;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.mvp_view.MainView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andychen on 2016/6/24.
 */
public class MainPresenter {
    private MainView mainView;
    private Context mContext;

    public MainPresenter(Context context,MainView mainView) {
        this.mainView = mainView;
        mContext = context;
    }

    public void switchActivity(View v){
        Intent intent = new Intent(mContext, SecondActivity.class);
        switch (v.getId()) {
            case R.id.btn:
                mainView.switchPage(intent);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                intent.setClass(mContext, ThirdActivity.class);
                mainView.switchPage(intent);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
                break;
        }

    }

}
