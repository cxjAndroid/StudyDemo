package com.example.jonchen.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jon on 3/17/17.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CompileScope {
}
