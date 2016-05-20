package com.gemslibe.instastats.activities;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gemslibe.instastats.mvp.presenters.SplashViewPresenter;
import com.gemslibe.instastats.mvp.views.SplashView;

/**
 * Created by kuzya on 21.04.2016.
 */
public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @InjectPresenter
    SplashViewPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashPresenter.attachView(this);
        mSplashPresenter.checkAuthorized();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        startActivity(new Intent(this, isAuthorized ? MainActivity.class : LoginActivity.class));
        finish();
    }
}
