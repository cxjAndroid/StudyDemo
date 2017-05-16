package com.example.jonchen.dagger;

import com.example.jonchen.activity.HomeActivity;

import dagger.Component;
import dagger.Module;

/**
 * Created by jon on 4/10/17.
 */

@Component(modules = HomeActivityModule.class)
public interface HomeActivityComponent {
    void inject(HomeActivity activity);
}
