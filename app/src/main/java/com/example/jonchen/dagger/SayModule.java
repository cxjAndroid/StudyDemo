package com.example.jonchen.dagger;

import com.example.jonchen.model.entity.Jack;
import com.example.jonchen.model.entity.Rose;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jon on 3/17/17.
 */


@Module
public class SayModule {

    @Provides
    @RoseSay
    public Say provideSay() {
        return new Rose();
    }
    @Provides
    @JackSay
    @ActivityScope
    public Jack provideJackSay() {
        return new Jack();
    }
}
