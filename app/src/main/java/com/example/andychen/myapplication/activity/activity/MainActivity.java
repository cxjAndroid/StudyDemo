package com.example.andychen.myapplication.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.mvp_presenter.BasePresenter;
import com.example.andychen.myapplication.activity.mvp_presenter.MainPresenter;
import com.example.andychen.myapplication.activity.mvp_view.MainView;
import com.example.andychen.myapplication.activity.utils.IntentUtils;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.iv)
    ImageView iv;
    private MainPresenter mainPresenter;
    private Subscription subscribe;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initDate() {
        MobclickAgent.openActivityDurationTrack(false);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        //被观察者
       /* Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("2");
                subscriber.onNext("3");
                subscriber.onCompleted();
            }
        });

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

        subscribe = observable.subscribe(subscriber);*/


        /*Observable.just("a","b","c","d").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogUtils.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.e(s);
            }
        });*/



    /*    subscribe = Observable.just("1", "2", "@")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e(s);
                    }
                });*/


        /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
        iv.setImageDrawable(bitmapDrawable);*/

        subscribe = Observable.just(R.mipmap.ic_launcher).map(new Func1<Integer, Drawable>() {
            @Override
            public Drawable call(Integer integer) {
                return new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            }
        }).subscribe(new Action1<Drawable>() {
            @Override
            public void call(Drawable drawable) {
                iv.setImageDrawable(drawable);
            }
        });

    }

    @OnClick({R.id.btn, R.id.btn1, R.id.iv})
    void click(View v) {
        mainPresenter.performOnClick(v);
        //startActivityForResult();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            ToastUtils.show("OK");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null) {
            subscribe.unsubscribe();
        }
    }

}
