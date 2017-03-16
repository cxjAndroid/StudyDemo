package com.example.jonchen.binding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.jonchen.databinding.ActivityDataBindingBinding;
import com.example.jonchen.model.entity.Person;
import com.example.jonchen.utils.ToastUtils;

/**
 * Created by jon on 3/15/17.
 */

public class EventHandler {
    private Context context;
    private ViewDataBinding viewDataBinding ;
    private Person person;

    public EventHandler(Context context) {
        this.context = context;
    }

    public EventHandler(Context context, ViewDataBinding viewDataBinding) {
        this.context = context;
        this.viewDataBinding = viewDataBinding;
    }

    public EventHandler(Context context, Person person) {
        this.context = context;
        this.person = person;
    }

    public void onClick(View v) {
        TextView textView = (TextView) v;
        ToastUtils.show(textView.getText() + "  click");
     /*   textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });*/
        ActivityDataBindingBinding viewDataBinding = (ActivityDataBindingBinding) this.viewDataBinding;
        person.setAge(18);
        person.setName("person change");

    }

    public boolean onTouch(View v, MotionEvent event){
        ToastUtils.show( "  onTouch  action" +event.getAction());
        return false;
    }
}
