package com.example.jonchen.activity;

import android.widget.Button;
import android.widget.ImageView;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.utils.LogUtils;

import butterknife.BindView;

/**
 * @author jon
 * @since 4/16/18
 */
public class CoordinateActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.image)
    ImageView image;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_coordinate;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int left = image.getLeft();
        int top = image.getTop();
        int right = image.getRight();
        int bottom = image.getBottom();
        LogUtils.e("  image.getLeft()------"+left);
        LogUtils.e("  image.getTop()------"+top);
        LogUtils.e("  image.getRight()------"+right);
        LogUtils.e("  image.getBottom()------"+bottom);
    }
}
