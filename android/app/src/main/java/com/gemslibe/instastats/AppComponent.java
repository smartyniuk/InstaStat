package com.gemslibe.instastats;

import com.gemslibe.instastats.activities.BaseActivity;
import com.gemslibe.instastats.mvp.presenters.DrawerHeaderPresenter;
import com.gemslibe.instastats.mvp.presenters.SplashViewPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kuzya on 20.05.2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface AppComponent {
    void inject(InstaStatApplication app);

    void inject(BaseActivity app);

    void inject(SplashViewPresenter splashViewPresenter);

    void inject(DrawerHeaderPresenter drawerHeaderPresenter);
}
