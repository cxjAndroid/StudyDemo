package com.example.andychen.myapplication.activity.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.event.EventMessage;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initDate() {
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn, R.id.btn1})
    void click(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        switch (v.getId()) {
            case R.id.btn:
                //Toast.makeText(this, "btn", Toast.LENGTH_LONG).show();
                startActivity(intent);
                EventBus.getDefault().postSticky(new EventMessage<>("send message"));
                break;
            case R.id.btn1:
                //Toast.makeText(this, "btn1", Toast.LENGTH_LONG).show();
                intent.setClass(this,ThirdActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void showMetrics() {
        /*
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int       displayWidth  = displayMetrics.widthPixels;
        int       displayHeight = displayMetrics.heightPixels;
        Toast.makeText(this,String.valueOf(displayWidth)+"---"+String.valueOf(displayHeight),Toast.LENGTH_SHORT).show();*/

        float density = getResources().getDisplayMetrics().density;
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Toast.makeText(this, String.valueOf(xdpi) + "-------" + String.valueOf(ydpi), Toast.LENGTH_SHORT).show();
        /*
        Display display = getWindowManager().getDefaultDisplay(); //Activity#getWindowManager()
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Toast.makeText(this,String.valueOf(width)+"---"+String.valueOf(height),Toast.LENGTH_SHORT).show();*/
    }

}
