package com.example.andychen.myapplication.activity.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.base.BaseApplication;
import com.example.andychen.myapplication.activity.bean.People;
import com.example.andychen.myapplication.activity.mvp_presenter.MainPresenter;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.NullStringToEmptyAdapterFactory;
import com.example.andychen.myapplication.activity.utils.RxUtils;
import com.example.andychen.myapplication.activity.view.EmptyLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    private MainPresenter mainPresenter;
    private Subscription subscribe;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
       /* EmptyLayout emptyLayout = new EmptyLayout(this);
        emptyLayout.setErrorType(1);
        addContentView(emptyLayout,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));*/
        EmptyLayout emptyLayout = (EmptyLayout) findViewById(R.id.status_layout);
        emptyLayout.setErrorType(EmptyLayout.NODATA);
    }

    @Override
    public void initDate() {
        MobclickAgent.openActivityDurationTrack(false);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);

        float density = BaseApplication.getApplication().getResources().getDisplayMetrics().density;
        float densityDpi = BaseApplication.getApplication().getResources().getDisplayMetrics().densityDpi;

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

        /*subscribe = Observable.just(R.mipmap.ic_launcher).map(new Func1<Integer, Drawable>() {
            @Override
            public Drawable call(Integer integer) {
                return new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), integer));
            }
        }).subscribe(new Action1<Drawable>() {
            @Override
            public void call(Drawable drawable) {
                iv.setImageDrawable(drawable);
            }
        });*/

        RxUtils.get().addList(
                Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(R.mipmap.ic_launcher);
                    }
                }).map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer resId) {
                        return new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), resId));
                    }
                }).subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {
                        iv.setImageDrawable(drawable);
                    }
                })
        );


        People people = new People();
        people.setName("cxj");
        people.setAge(26);
      /*  Gson gson = new Gson();
        String s = gson.toJson(people);

        People people1 = gson.fromJson(s, People.class);
        LogUtils.e(s);*/

        Gson gson = new GsonBuilder().serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                .create();
        String s = gson.toJson(people);
        LogUtils.e(s);


        People convertPeople = gson.fromJson(s, People.class);

        LogUtils.e(s);
        //new GsonBuilder().create().serializeNulls()

    }

    @OnClick({R.id.btn, R.id.btn1, R.id.iv})
    void click(View v) {
        mainPresenter.performOnClick(v);
        //startActivityForResult();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            tv.setText(extras.getString("result"));
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
