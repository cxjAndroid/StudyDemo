package com.example.andychen.myapplication.activity.mvp_presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.activity.SecondActivity;
import com.example.andychen.myapplication.activity.activity.ThirdActivity;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.utils.IntentUtils;
import com.example.andychen.myapplication.activity.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andychen on 2016/7/1.
 */
public class MainPresenter extends BasePresenter {

    private Context context;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void performOnClick(View v) {
        super.performOnClick(v);
        switch (v.getId()) {
            case R.id.btn:
                IntentUtils.startActivityLeftIn((Activity) context, SecondActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                IntentUtils.startActivityLeftIn((Activity) context, ThirdActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
                break;
            case R.id.iv:
                Activity activity = (Activity) this.context;
                activity.startActivityForResult(new Intent(activity,SecondActivity.class),0);
                break;
        }
    }
}
