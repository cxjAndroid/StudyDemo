package com.example.jonchen.activity;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.dagger.DaggerTestComponent;
import com.example.jonchen.model.entity.Chen;

import javax.inject.Inject;

/**
 * Created by jon on 4/10/17.
 */

public class DaggerDemo2Activity extends BaseActivity {

    @Inject
    Chen chen;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_dagger;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        DaggerTestComponent.create().inject(this);
        chen.showXujunSay();
    }
}
