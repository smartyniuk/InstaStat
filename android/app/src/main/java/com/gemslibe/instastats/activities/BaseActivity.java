package com.gemslibe.instastats.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gemslibe.instastats.AppComponent;
import com.gemslibe.instastats.InstaStatApplication;
import com.gemslibe.instastats.R;

/**
 * Created by kuzya on 20.05.2016.
 */
public class BaseActivity extends MvpAppCompatActivity {
    private boolean mIsRestarted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getBackendComponent().inject(this);
    }

    AppComponent getBackendComponent() {
        return ((InstaStatApplication) getApplication()).getAppComponent();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mIsRestarted) {
            slideInRightPageTransition();
        } else {
            slideInLeftPageTransition();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mIsRestarted = true;
    }

    protected void slideInRightPageTransition() {

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private void slideInLeftPageTransition() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
