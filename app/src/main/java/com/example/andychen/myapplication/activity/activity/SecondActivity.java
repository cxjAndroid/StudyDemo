package com.example.andychen.myapplication.activity.activity;

import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.Toast;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.event.EventMessage;
import com.example.andychen.myapplication.activity.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andychen on 2016/6/1.
 */
public class SecondActivity extends BaseActivity {

    @BindView(R.id.btn_second)
    Button btn_second;

    private ViewConfiguration viewConfiguration;

    @Override
    public void initView() {
        setContentView(R.layout.activity_second);

    }

    @Override
    public void initDate() {
        registerEventBus();
        ButterKnife.bind(this);
        viewConfiguration = ViewConfiguration.get(this);
        setResult(RESULT_OK);
    }


    @OnClick(R.id.btn_second)
    void click() {
        //获取touchSlop。该值表示系统所能识别出的被认为是滑动的最小距离
        int touchSlop = viewConfiguration.getScaledTouchSlop();
        //获取Fling速度的最小值和最大值
        int minimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        int maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        //判断是否有物理按键
        boolean isHavePermanentMenuKey = viewConfiguration.hasPermanentMenuKey();
        LogUtils.e("touchSlop:"+String.valueOf(touchSlop));
        LogUtils.e("minimumVelocity:"+String.valueOf(minimumVelocity));
        LogUtils.e("maximumVelocity:"+String.valueOf(maximumVelocity));
        LogUtils.e("isHavePermanentMenuKey:"+isHavePermanentMenuKey);
    }

    @Subscribe(sticky = true)
    public void onEvent(EventMessage msg) {
        Toast.makeText(this, (CharSequence) msg.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
