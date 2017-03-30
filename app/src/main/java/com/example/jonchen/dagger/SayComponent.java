package com.example.jonchen.dagger;

import com.example.jonchen.model.entity.Jack;

import javax.inject.Singleton;

import dagger.Component;

/**2
 * Created by jon on 3/17/17.
 */


/*@Component(modules = SayModule.class)
@ActivityScope*/
@Singleton
@Component(modules = SayModule.class)
public interface SayComponent {
 /*   @RoseSay
    Say getRoseSay();
    @JackSay
    Jack getJackSay();*/

    @JonSay
    Say getJonSay();

    @JackSay
    Say getJackSay();

}
