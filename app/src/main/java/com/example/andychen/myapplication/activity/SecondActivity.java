package com.example.andychen.myapplication.activity;

import android.widget.Toast;

import com.example.andychen.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andychen on 2016/6/1.
 */
public class SecondActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initDate() {
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_second)
    void click() {
        Toast.makeText(this, "second", Toast.LENGTH_SHORT).show();
    }

}
