package com.gemslibe.instastats.mvp.presenters;

import com.gemslibe.instastats.model.AppModel;
import com.gemslibe.instastats.mvp.views.SplashView;

import javax.inject.Inject;

/**
 * Created by kuzya on 21.05.2016.
 */
public class SplashViewPresenter extends PresenterBase<SplashView> {

    @Inject
    AppModel mAppModel;

    public SplashViewPresenter() {
        super();
        getAppComponent().inject(this);
    }

    public void checkAuthorized() {
        for (SplashView splashView : getAttachedViews()) {
            splashView.setAuthorized(mAppModel.isLoggedIn());
        }
    }
}
