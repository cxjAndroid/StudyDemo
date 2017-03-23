package com.example.jonchen.view;

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
    RectF rectFArc = new RectF(250, 900, 500, 1150);


    private int startX;
    private int startY;
    private int stopX;
    private int stopY;
    private int width;
    private int height;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAction(int startX,int startY,int stopX,int stopY){
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        width = 0;
        height = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = 500;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = 1150;
        }

        //为了将小球绘制到画布中央
        stopX = width/2;
        startY = height/2;

        setMeasuredDimension(width, height);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawCircle(150, 100, 50, paint);

        canvas.drawRoundRect(
                rectF, 18,
                18, paint);

        paint.reset();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.translate(100f,0);
        canvas.drawCircle(50, 100, 50, paint);

        paint.reset();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);

        canvas.drawRoundRect(rectF, 30, 30, paint);

        paint.reset();
        paint.setColor(Color.LTGRAY);
        paint.setAntiAlias(true);
        canvas.drawOval(rectFOral, paint);

        paint.reset();
        paint.setColor(Color.MAGENTA);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawArc(rectFArc, -270, 270, true, paint);

        /*paint.reset();
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        paint.setTextSize(100);
        //绘制文本
        canvas.drawText("jEh", 700, 250, paint);*/
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(stopX,startY,50,paint);


    }
}
