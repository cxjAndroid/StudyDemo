package com.example.andychen.myapplication.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.base.BaseApplication;
import com.example.andychen.myapplication.activity.base.BaseListAdapter;
import com.example.andychen.myapplication.activity.base.BaseViewHolder;
import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.bean.People;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.mvp_presenter.MainPresenter;
import com.example.andychen.myapplication.activity.mvp_view_interface.MainView;
import com.example.andychen.myapplication.activity.utils.IntentUtils;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.NullStringToEmptyAdapterFactory;
import com.example.andychen.myapplication.activity.utils.RxUtils;
import com.example.andychen.myapplication.activity.view.MyListView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.mListView)
    MyListView mListView;
    private Subscription subscribe;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        //addContentView(emptyLayout,
        // new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void initDate() {
        MobclickAgent.openActivityDurationTrack(false);


        float density = BaseApplication.getApplication().getResources().getDisplayMetrics().density;
        float densityDpi = BaseApplication.getApplication().getResources().getDisplayMetrics().densityDpi;

       /* rxDemo();
        GsonDemo();*/
        showLoadingPage();
        mPresenter.getDoctorList();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this,this);
    }

    @Override
    public void RefreshDocList(List<Doctor> doctorList) {
        MyAdapter myAdapter = new MyAdapter(this, doctorList, R.layout.item_doctor);
        mListView.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseListAdapter<Doctor> {
        public MyAdapter(Context context, List<Doctor> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void refreshView(BaseViewHolder holder, Doctor doctor, int p) {
            TextView text_dep = holder.getView(R.id.text_dep);
            TextView text_doc = holder.getView(R.id.text_doc);
            SimpleDraweeView image_doc = holder.getView(R.id.image_doc);

            text_dep.setText(doctor.getDepartmentName());
            text_doc.setText(doctor.getDoctorName());
            image_doc.setImageURI(Uri.parse(doctor.getThumbnail()));
        }
    }


    @OnClick({R.id.btn, R.id.btn1, R.id.iv})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn:
                //mPresenter.click();
                IntentUtils.startActivityLeftIn(this, BannerActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                IntentUtils.startActivityLeftIn(this, ThirdActivity.class);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
                break;
            case R.id.iv:
                break;
            case R.id.mListView:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            tv.setText(extras.getString("result"));
        }
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
    }


}
