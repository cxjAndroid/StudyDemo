package com.example.andychen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenxujun on 2016/6/21.
 */
public class MyView extends View {

    Paint paint = new Paint();
    RectF rectF = new RectF(250, 10, 350, 50);
    RectF rectFOral = new RectF(250, 400, 500, 800);
    RectF rectFArc = new RectF(250,1200,500,1450);

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        canvas.drawCircle(150,100,50,paint);


        canvas.drawRoundRect(
                rectF, 18,
                18, paint);


        /*paint.reset();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(150,250,50,paint);

        paint.reset();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);

        canvas.drawRoundRect(rectF,30,30,paint);

        paint.reset();
        paint.setColor(Color.LTGRAY);
        paint.setAntiAlias(true);
        canvas.drawOval(rectFOral,paint);

        paint.reset();
        paint.setColor(Color.MAGENTA);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawArc(rectFArc,-90,270,true,paint);

        paint.reset();
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        paint.setTextSize(100);
        //绘制文本
        canvas.drawText("jEh", 800, 250, paint);*/

    }
}
