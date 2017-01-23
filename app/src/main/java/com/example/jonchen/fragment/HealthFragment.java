package com.example.jonchen.fragment;

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
    public int getContentViewLayoutID() {
        return R.layout.fragment_health;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    public void setText(String text) {
        mTextView.setText(text);
    }
}
