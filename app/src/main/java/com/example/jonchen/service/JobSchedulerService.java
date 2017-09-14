package com.example.jonchen.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

import com.example.jonchen.base.BaseApplication;

/**
 * Created by jon on 9/1/17.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        startAlarmService();
        //jobFinished(params, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        //startAlarmService();
        return false;
    }

    public void startAlarmService() {
        Intent intent = new Intent(BaseApplication.getApplication(), AlarmService.class);
        startService(intent);
    }
}
