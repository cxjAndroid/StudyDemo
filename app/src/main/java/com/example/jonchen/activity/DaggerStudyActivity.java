package com.example.jonchen.activity;

import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.dagger.DaggerSayComponent;
import com.example.jonchen.dagger.DaggerStudyComponent;
import com.example.jonchen.dagger.JackSay;
import com.example.jonchen.dagger.JonSay;
import com.example.jonchen.dagger.Say;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by jon on 3/17/17.
 */

public class DaggerStudyActivity extends BaseActivity {

  /*  @Inject
    @JackSay
    Jack jackSay;

    @Inject
    @JackSay
    Jack jackSay2;*/

  /*  @Inject
    Rose rose;*/

    @Inject
    @JackSay
    Say jackSay;

    @BindView(R.id.showText)
    TextView showText;

    @Inject
    @JonSay
    Say jon;


    @Inject
    @JonSay
    Say jon2;


    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_dagger;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {


        //DaggerStudyComponent.create().Inject(this);
      /*  DaggerStudyComponent.builder().sayComponent(new DaggerSayComponent.create())
                .build().Inject(this);*/


        DaggerStudyComponent.builder().sayComponent(DaggerSayComponent.create()).build().Inject(this);


        jon.sayWhat();
        showText.setText(jon+"----"+jon2);
        //jackSay.sayWhat();

       /* DaggerStudyComponent
                .builder()
                .sayComponent(DaggerSayComponent.create())
                .build()
                .Inject(this);
        //whoSay.sayWhat();
        //jackSay.sayWhat();*/


    }
}
