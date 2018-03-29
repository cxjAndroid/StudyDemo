package com.example.jonchen.activity;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.base.BaseListAdapter;
import com.example.jonchen.base.BaseViewHolder;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.pattern.state.AliPayState;
import com.example.jonchen.pattern.state.WeChatPayState;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.pattern.state.PayStateController;
import com.example.jonchen.utils.PopupWindowUtils;
import com.example.jonchen.view.MyBtn;
import com.example.jonchen.view.MyLayout;
import com.example.jonchen.view.MyListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jon on 3/18/17.`
 */

public class ActionActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    @BindView(R.id.mLayout)
    MyLayout mLayout;

    @BindView(R.id.aliPay)
    MyBtn aliPay;
    @BindView(R.id.wxPay)
    MyBtn wxPay;

    @BindView(R.id.mListView)
    MyListView mListView;
    private Thread thread;
    private PayStateController payStateController;


    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_action;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void initData() {
        mLayout.setOnTouchListener(this);

        aliPay.setOnClickListener(this);
        mLayout.setOnClickListener(this);
        wxPay.setOnClickListener(this);

        EventBus.getDefault().post(new EventMessage<>("hahaha"));


        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(1);
        }

        MyAdapter myAdapter = new MyAdapter(this, list, R.layout.item_watch);
        mListView.setAdapter(myAdapter);



        /*   while(true){

           }*/

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!thread.isInterrupted()) {
                    LogUtils.e("no interrupt");
                }
                LogUtils.e("interrupt");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    LogUtils.e("interrupt");
                }
            }
        });
        thread.start();

        payStateController = new PayStateController();
    }


    class MyAdapter extends BaseListAdapter<Integer> {
        public MyAdapter(Context context, List data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void refreshView(BaseViewHolder holder, Integer o, int p) {
            LinearLayout contentLL = holder.getView(R.id.contentLL);

            //String name = contentLL.getParent().getClass().getName();
        }
    }


    @Override
    protected void onDestroy() {
        thread.interrupt();

        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {
        //LogUtils.e("onClick----"+v.getId());
        switch (v.getId()) {
            case R.id.aliPay:
                payStateController.setPayState(new AliPayState());
                break;
            case R.id.wxPay:
                payStateController.setPayState(new WeChatPayState());
                PopupWindowUtils popupWindowUtils = new PopupWindowUtils();
                View view = View.inflate(this, R.layout.activity_view, null);
                popupWindowUtils.showPop(wxPay, view);
                break;
        }
        payStateController.pay();
    }
}
