package com.example.jonchen.dagger;

import com.example.jonchen.activity.StudyDaggerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jon on 3/17/17.
 */

@Component(dependencies = SayComponent.class)
@CompileScope
public interface StudyComponent {

    void Inject(StudyDaggerActivity activity);
}
