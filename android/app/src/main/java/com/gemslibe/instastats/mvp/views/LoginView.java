package com.gemslibe.instastats.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by kuzya on 21.05.2016.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface LoginView extends MvpView {
    void showLoggedIn();

    void showLoggedOut();

    void showError();
}
