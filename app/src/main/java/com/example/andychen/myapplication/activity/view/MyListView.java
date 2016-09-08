package com.example.andychen.myapplication.activity.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by chenxujun on 2015/5/14.
 * ListView基类
 */
public class MyListView extends ListView {
    private boolean isInScrollView;

    public boolean isInScrollView() {
        return isInScrollView;
    }

    public void setIsInScrollView(boolean isInScrollView) {
        this.isInScrollView = isInScrollView;
    }

    public MyListView(Context context) {
        super(context);
        initView();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        //setDivider(getResources().getDrawable(R.color.divide_light_gray));
        //setDivider(getResources().getDrawable(R.drawable.shape_list_divider));
        setDividerHeight(1);
        setOverScrollMode(OVER_SCROLL_NEVER);
        setCacheColorHint(Color.TRANSPARENT);
        setVerticalScrollBarEnabled(false);
        //setSelector(getResources().getDrawable(R.drawable.selector_list_item));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(isInScrollView){
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
