package com.example.jonchen.dagger;

import com.example.jonchen.model.entity.Rose;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jon on 3/17/17.
 */

@Module
public class RoseModule {
    @Provides
    public Rose provideRose() {
        return new Rose();
    }
}
