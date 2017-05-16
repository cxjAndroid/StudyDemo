package com.example.jonchen.dagger;

import com.example.jonchen.activity.DaggerDemo2Activity;

import dagger.Component;

/**
 * Created by jon on 4/10/17.
 */

@Component
public interface TestComponent {
    void inject(DaggerDemo2Activity daggerDemo2Activity);
}
