package com.example.jonchen.annotation;

import android.view.View;

import java.lang.reflect.AnnotatedElement;

/**
 * @author 17041931
 * @since 2017/11/20
 */

public interface ProcessInt<T extends AnnotatedElement> {

    public boolean accept(AnnotatedElement annotatedElement);

    public void process(Object object, View view, T t);
}
