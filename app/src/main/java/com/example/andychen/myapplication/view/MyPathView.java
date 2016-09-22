package com.example.andychen.myapplication.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenxujun on 2016/6/22.
 */
public class MyPathView extends View {
    Paint paint;
    Path path = new Path();

    public MyPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public MyPathView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyPathView(Context context) {
        this(context,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setViewPaint();
        path.moveTo(300,300);
        path.lineTo(200,500);
        path.lineTo(400,500);
        path.close();
        canvas.drawPath(path,paint);
    }

    public void setViewPaint(){
        //绘制风格
        paint=new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        //设置绘制颜色
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        //为了方便看Path的路径效果
        //设置绘制风格为空心
        paint.setStyle(Paint.Style.STROKE);
        //设置空心边框的宽度
        paint.setStrokeWidth(10);
    }
}
