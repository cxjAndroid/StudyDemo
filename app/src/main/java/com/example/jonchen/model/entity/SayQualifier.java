package com.example.jonchen.model.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by jon on 3/17/17.
 */


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface SayQualifier {
    String value() default "";
}
