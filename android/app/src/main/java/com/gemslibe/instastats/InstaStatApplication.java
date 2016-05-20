package com.gemslibe.instastats;

import android.app.Application;

import com.gemslibe.instastats.model.AppModel;

import javax.inject.Inject;

/**
 * Created by kuzya on 20.05.2016.
 */
public class InstaStatApplication extends Application {

    private static InstaStatApplication mInstance;
    @Inject
    protected AppModel mAppModel;
    private AppComponent mAppComponent;

    public static InstaStatApplication getInstance() {
        return mInstance;
    }

 /*   @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mAppComponent.inject(this);

        mInstance = this;

        mAppModel.load();
    }

    public AppComponent getAppComponent() {
        return this.mAppComponent;
    }

    public AppModel getAppModel() {
        return mAppModel;
    }
}
