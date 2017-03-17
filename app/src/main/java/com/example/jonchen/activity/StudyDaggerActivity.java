package com.example.jonchen.activity;

import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.dagger.DaggerSayComponent;
import com.example.jonchen.dagger.DaggerStudyComponent;
import com.example.jonchen.dagger.JackSay;
import com.example.jonchen.model.entity.Jack;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by jon on 3/17/17.
 */

public class StudyDaggerActivity extends BaseActivity {

    @Inject
    @JackSay
    Jack jackSay;

    @Inject
    @JackSay
    Jack jackSay2;

    @BindView(R.id.showText)
    TextView showText;


    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_dagger;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        DaggerStudyComponent
                .builder()
                .sayComponent(DaggerSayComponent.create())
                .build()
                .Inject(this);
        //whoSay.sayWhat();
        //jackSay.sayWhat();
        showText.setText(jackSay+"----"+jackSay2);

    }
}
