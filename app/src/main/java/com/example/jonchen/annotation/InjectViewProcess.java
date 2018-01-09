package com.example.jonchen.annotation;

import android.view.View;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * @author 17041931
 * @since 2017/11/20
 */

public class InjectViewProcess implements ProcessInt<Field> {
    @Override
    public boolean accept(AnnotatedElement annotatedElement) {
        return annotatedElement.isAnnotationPresent(InjectView.class);
    }

    @Override
    public void process(Object object, View view, Field field) {
        
        InjectView injectView = field.getAnnotation(InjectView.class);
        int viewId = injectView.value();
        View findView = view.findViewById(viewId);
        field.setAccessible(true);
        try {
            field.set(object,findView);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
