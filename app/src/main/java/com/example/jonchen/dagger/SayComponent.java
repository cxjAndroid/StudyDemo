package com.example.jonchen.dagger;

import com.example.jonchen.model.entity.Jack;

import dagger.Component;

/**
 * Created by jon on 3/17/17.
 */


@Component(modules = SayModule.class)
@ActivityScope
public interface SayComponent {
    @RoseSay
    Say getRoseSay();
    @JackSay
    Jack getJackSay();
}
