package com.gemslibe.instastats.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.gemslibe.instastats.InstaStatApplication;

/**
 * Created by kuzya on 21.04.2016.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (InstaStatApplication.getInstance().getAppModel().isLoggedIn())
            startActivity(new Intent(this, MainActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

        finish();
    }
}
