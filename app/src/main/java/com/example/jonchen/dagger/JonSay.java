package com.example.jonchen.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by jon on 3/29/17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface JonSay {
}
