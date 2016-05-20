package com.gemslibe.instastats.model;

import com.gemslibe.instastats.instagram.engine.InstagramEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by kuzya on 20.05.2016.
 */
public class AppModel {
    private static Logger _logger = LoggerFactory.getLogger(AppModel.class.getName());

    private boolean mIsLoggedIn = false;

    private InstagramEngine mIgEngine;

    @Inject
    public AppModel(InstagramEngine igEngine) {
        mIgEngine = igEngine;
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void load() {
        _logger.debug("load");
        try {
            mIgEngine.getSession();
            mIsLoggedIn = true;
        } catch (Exception e) {
            mIsLoggedIn = false;
        }
    }
}
