package com.gemslibe.instastats.mvp.presenters;

import com.arellomobile.mvp.viewstate.MvpViewState;
import com.gemslibe.instastats.instagram.engine.InstagramEngine;
import com.gemslibe.instastats.instagram.exceptions.InstagramException;
import com.gemslibe.instastats.instagram.interfaces.InstagramAPIResponseCallback;
import com.gemslibe.instastats.instagram.objects.IGPagInfo;
import com.gemslibe.instastats.instagram.objects.IGUser;
import com.gemslibe.instastats.model.UserModel;
import com.gemslibe.instastats.mvp.views.DrawerHeaderView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import javax.inject.Inject;

/**
 * Created by kuzya on 21.05.2016.
 */

public class DrawerHeaderPresenter extends PresenterBase<DrawerHeaderView> {
    public static final String TAG = "DrawerHeaderPresenter";
    private static Logger _logger = LoggerFactory.getLogger(DrawerHeaderPresenter.class.getSimpleName());

    @Inject
    InstagramEngine instagram;
    UserModel userModel;

    public DrawerHeaderPresenter() {
        getAppComponent().inject(this);
    }

    public void loadUserInfo() {
        for (DrawerHeaderView view : getAttachedViews()) {
            view.showProgress(true);
        }

        instagram.getUserDetails(new InstagramAPIResponseCallback<IGUser>() {
            @Override
            public void onResponse(IGUser responseObject, IGPagInfo pageInfo) {
                userModel = new UserModel();
                userModel.setImageUrl(responseObject.getProfilePictureURL());
                userModel.setMediaCount(responseObject.getMediaCount());
                userModel.setFollowsCount(responseObject.getFollowsCount());
                userModel.setFollowedCount(responseObject.getFollowedByCount());
                userModel.setUserName(responseObject.getUsername());

                Set<DrawerHeaderView> views = getAttachedViews();
                for (DrawerHeaderView view : views) {
                    view.setUserModel(userModel);
                    view.showProgress(false);
                }
            }

            @Override
            public void onFailure(InstagramException exception) {
                _logger.error(exception.getMessage());
                exception.printStackTrace();
            }
        });
    }

    @Override
    public void attachView(DrawerHeaderView view) {
        super.attachView(view);
    }

    @Override
    public void setViewState(MvpViewState<DrawerHeaderView> viewState) {
        super.setViewState(viewState);
    }
}
