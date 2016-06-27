package com.example.andychen.myapplication.activity.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.mvp_presenter.MainPresenter;
import com.example.andychen.myapplication.activity.mvp_view.MainView;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.MetricsUtils;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

public class MainActivity extends BaseActivity implements MainView{

    @BindView(R.id.btn)
    Button btn;
    private MainPresenter mainPresenter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initDate() {
        MobclickAgent.openActivityDurationTrack(false);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this,this);

        //rxJAVA
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogUtils.e("completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.e(s);
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("2");
                subscriber.onNext("3");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(subscriber);

    }

    @OnClick({R.id.btn, R.id.btn1})
    void click(View v) {
        mainPresenter.switchActivity(v);
    }

    @Override
    public void switchPage(Intent intent) {
        startActivity(intent);
    }
}
