package com.gemslibe.instastats;

import android.content.Context;

import com.gemslibe.instastats.instagram.engine.InstagramEngine;
import com.gemslibe.instastats.model.AppModel;
import com.gemslibe.instastats.services.local.AppPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final InstaStatApplication mApp;
    private final Context mContext;

    ApplicationModule(InstaStatApplication app) {
        mApp = app;
        mContext = app.getApplicationContext();
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    protected InstaStatApplication provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    protected AppPrefs provideAppPrefs() {
        return new AppPrefs(mApp);
    }

    @Provides
    @Singleton
    protected AppModel provideAppModel() {
        return new AppModel(provideInstagramApp());
    }

    @Provides
    @Singleton
    protected InstagramEngine provideInstagramApp() {
        return InstagramEngine.getInstance(mContext);
    }
}
