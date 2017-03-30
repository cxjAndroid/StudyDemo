package com.example.jonchen.dagger;

import com.example.jonchen.model.entity.Jon;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jon on 3/29/17.
 */


@Module
public class JonModule {
    @Provides
    @JonSay
    public Say provideJon() {
        return new Jon();
    }
}
