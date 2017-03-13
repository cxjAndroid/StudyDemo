package com.example.jonchen.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.model.entity.People;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;


/**
 * Created by chenxujun on 16-9-9.
 */
public class TestActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    @BindView(R.id.contentRL)
    RelativeLayout contentRL;
    @BindView(R.id.btnTest)
    Button btnTest;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_intercept;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void initData() {
        //rxDemo();
        EventBus.getDefault().post(new EventMessage<>("haha"));

        btnTest.setOnTouchListener(this);
        contentRL.setOnTouchListener(this);
        contentRL.setOnClickListener(this);
        btnTest.setOnClickListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        LogUtils.e("onTouch   " + "action:"+event.getAction()+"----"+v.getClass().getSimpleName());

        return false;
    }

    @Override
    public void onClick(View v) {
        LogUtils.e("onClick   " + v.getClass().getSimpleName());
    }

    private void GsonDemo() {
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

    private void rxDemo() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("onNext1");
                subscriber.onNext("onNext2");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
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
        });


        Observable.just("onNextJust1", "onNextJust2").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                LogUtils.e(s);
            }
        });


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



    /*  subscribe = Observable.just("1", "2", "@")
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

       /* RxUtils.get().addList(
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
                }).subscribe(new Action17<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {
                        iv.setImageDrawable(drawable);
                    }
                })
        );*/
    }
}
