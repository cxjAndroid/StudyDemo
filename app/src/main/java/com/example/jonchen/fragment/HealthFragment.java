package com.example.jonchen.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by chenxujun on 2016/7/19.
 */
public class HealthFragment extends BaseFragment {
    @BindView(R.id.mTextView)
    TextView mTextView;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_health,null);
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
