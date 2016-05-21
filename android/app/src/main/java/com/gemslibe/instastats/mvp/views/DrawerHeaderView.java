package com.gemslibe.instastats.mvp.views;

import com.arellomobile.mvp.GenerateViewState;
import com.arellomobile.mvp.MvpView;
import com.gemslibe.instastats.model.UserModel;

/**
 * Created by kuzya on 21.05.2016.
 */

public interface DrawerHeaderView extends MvpView {

    void showProgress(boolean inProgress);
    void setUserModel(UserModel userModel);
}
