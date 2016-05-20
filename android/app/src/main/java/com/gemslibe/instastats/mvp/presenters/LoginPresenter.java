package com.gemslibe.instastats.mvp.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.gemslibe.instastats.instagram.engine.InstagramEngine;
import com.gemslibe.instastats.instagram.engine.InstagramKitConstants;
import com.gemslibe.instastats.instagram.utils.InstagramKitLoginScope;
import com.gemslibe.instastats.mvp.views.LoginView;

/**
 * Created by kuzya on 21.05.2016.
 */
@InjectViewState
public class LoginPresenter extends PresenterBase<LoginView> {

    public String[] getScopes() {
        return scopes;
    }

    String[] scopes = {
            InstagramKitLoginScope.BASIC,
            InstagramKitLoginScope.COMMENTS,
            InstagramKitLoginScope.LIKES,
            InstagramKitLoginScope.RELATIONSHIP,
            InstagramKitLoginScope.PUBLIC_ACCESS,
            InstagramKitLoginScope.FOLLOWER_LIST};

    public void onLoginResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case InstagramEngine.REQUEST_CODE_LOGIN:

                if (resultCode == Activity.RESULT_OK) {

                    Bundle bundle = data.getExtras();

                    if (bundle.containsKey(InstagramKitConstants.kSessionKey)) {
//                        IGSession session = (IGSession) bundle.getSerializable(InstagramKitConstants.kSessionKey);
                        getViewState().showLoggedIn();
                    } else {
                        getViewState().showError();
                    }
                } else {
                    getViewState().showError();
                }
                break;
            case InstagramEngine.REQUEST_CODE_LOGOUT:
                if (resultCode == Activity.RESULT_OK) {
                    getViewState().showLoggedOut();
                } else {
                    getViewState().showError();
                }
            default:
                break;
        }
    }
}
