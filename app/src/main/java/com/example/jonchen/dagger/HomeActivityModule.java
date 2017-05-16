package com.example.jonchen.dagger;

import com.example.jonchen.mvpview.HomeView;
import com.example.jonchen.presenter.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jon on 4/10/17.
 */

@Module
public class HomeActivityModule {

    private HomeView homeView;

    public HomeActivityModule(HomeView mainView) {
        this.homeView = mainView;
    }

    @Provides
    public HomeView provideMainView() {
        return homeView;
    }

    @Provides
    public HomePresenter provideMainPresenter() {
        return new HomePresenter(homeView);
    }
}
