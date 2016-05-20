package com.gemslibe.instastats.mvp.presenters;


import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.gemslibe.instastats.AppComponent;
import com.gemslibe.instastats.InstaStatApplication;

/**
 * Created by kuzya on 21.05.2016.
 */
public class PresenterBase<V extends MvpView> extends MvpPresenter<V> {

    protected AppComponent getAppComponent() {
        return InstaStatApplication.getInstance().getAppComponent();
    }
}
