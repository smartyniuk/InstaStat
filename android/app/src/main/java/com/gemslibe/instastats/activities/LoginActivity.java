package com.gemslibe.instastats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gemslibe.instastats.R;
import com.gemslibe.instastats.instagram.activities.InstagramAuthActivity;
import com.gemslibe.instastats.instagram.engine.InstagramEngine;
import com.gemslibe.instastats.mvp.presenters.LoginPresenter;
import com.gemslibe.instastats.mvp.views.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginView {

    @Bind(R.id.btnLogin)
    protected Button loginButton;
    @InjectPresenter
    LoginPresenter presenter;

    View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, InstagramAuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);

            intent.putExtra(InstagramEngine.TYPE, InstagramEngine.TYPE_LOGIN);
            intent.putExtra(InstagramEngine.SCOPE, presenter.getScopes());

            startActivityForResult(intent, InstagramEngine.REQUEST_CODE_LOGIN);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.attachView(this);

        loginButton.setOnClickListener(loginOnClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        presenter.onLoginResult(requestCode, resultCode, data);
    }

    @Override
    public void showLoggedIn() {
        Toast.makeText(this, "Logged In Successfully.",
                Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showLoggedOut() {
        Toast.makeText(this, "Logged Out Successfully.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Login Error",
                Toast.LENGTH_LONG).show();
    }
}
