package com.example.jonchen.dagger;

import com.example.jonchen.activity.DaggerStudyActivity;

import dagger.Component;

/**
 * Created by jon on 3/17/17.
 */

//@Component(dependencies = SayComponent.class)
@CompileScope
@Component(dependencies = SayComponent.class)
public interface StudyComponent {

    void Inject(DaggerStudyActivity activity);
}
