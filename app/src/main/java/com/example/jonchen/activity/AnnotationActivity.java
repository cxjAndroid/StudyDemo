package com.example.jonchen.activity;

import android.view.View;
import android.widget.Button;

import com.example.jonchen.R;
import com.example.jonchen.annotation.EnumSex;
import com.example.jonchen.annotation.InjectName;
import com.example.jonchen.annotation.InjectString;
import com.example.jonchen.annotation.InjectView;
import com.example.jonchen.annotation.Injector;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.model.entity.People;
import com.example.jonchen.utils.ToastUtils;
import com.example.viewinject.BindView;
import com.example.viewinject.ViewInjector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 17041931
 * @since 2017/11/14
 */

public class AnnotationActivity extends BaseActivity {

    @InjectName("jon chen")
    private String name;
    @InjectString("cxjbreaking@icould.com")
    private String email;

    @InjectView(R.id.annotationBtn1)
    private Button annotationBtn1;
    @InjectView(R.id.annotationBtn2)
    private Button annotationBtn2;
    @BindView(R.id.annotationBtn3)
    Button annotationBtn3;
   /* @InjectView(R.id.annotationBtn4)
    private Button annotationBtn4;*/

    private Button annotationBtn4;

    @EnumSex(sex = EnumSex.Sex.BOY)
    private String sex;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_annotation;
    }

    @Override
    protected void initView() {
        Injector.inject(this);
        ViewInjector.bind(this);
        annotationBtn1.setText("annotationBtn1");
        annotationBtn2.setText("annotationBtn2");
        annotationBtn3.setText("annotationBtn3");
        //annotationBtn4.setText("annotationBtn4");
        annotationBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {
        try {
            Field name = getClass().getDeclaredField("name");
            if (name.isAnnotationPresent(InjectName.class)) {
                InjectName annotation = name.getAnnotation(InjectName.class);
                name.setAccessible(true);
                name.set(this, annotation.value());
            }

            Field email = getClass().getDeclaredField("email");
            if (email.isAnnotationPresent(InjectString.class)) {
                InjectString annotation = email.getAnnotation(InjectString.class);
                email.setAccessible(true);
                email.set(this, setEmail(annotation.value()));
                ToastUtils.show(this.email);

                /*Class<?> aClass = Class.forName("com.example.jonchen.model.entity.People");
                Method emailAddress = aClass.getMethod("setEmailAddress", String.class);
                People people = (People) aClass.newInstance();
                emailAddress.invoke(people, annotation.value());
                ToastUtils.show(people.getEmailAddress());*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }
}
