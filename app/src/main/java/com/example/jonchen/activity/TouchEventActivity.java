package com.example.jonchen.activity;

import android.view.MotionEvent;
import android.view.View;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.view.MyBtn;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * @author jon
 * @since 9/14/17
 */

public class TouchEventActivity extends BaseActivity {
    @BindView(R.id.btn_my)
    MyBtn btnMy;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_touch;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_my:
                LogUtils.e(view.getId() + "--------onClick");
                break;
        }
    }

    @OnTouch({R.id.btn_my})
    public boolean onTouch(View v, MotionEvent event) {
        LogUtils.e(v.getId() + "----------onTouch--------" + event.getAction());
        return false;
    }

}
