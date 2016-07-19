package com.example.andychen.myapplication.activity.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andychen.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andychen on 2016/7/19.
 */
public class HealthFragment extends BaseFragment {
    @BindView(R.id.mTextView)
    TextView mTextView;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_health,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void setText(String text){
        mTextView.setText(text);
    }


}
