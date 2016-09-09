package com.example.andychen.myapplication.activity.activity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.bean.People;
import com.example.andychen.myapplication.activity.mvp_model.BaseActivity;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.NullStringToEmptyAdapterFactory;
import com.example.andychen.myapplication.activity.utils.RxUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by chenxujun on 16-9-9.
 */
public class TestActivity extends BaseActivity {

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

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
                }).subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {
                        iv.setImageDrawable(drawable);
                    }
                })
        );*/
    }
}
