package com.gemslibe.instastats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gemslibe.instastats.R;
import com.gemslibe.instastats.instagram.activities.InstagramAuthActivity;
import com.gemslibe.instastats.instagram.engine.InstagramEngine;
import com.gemslibe.instastats.instagram.engine.InstagramKitConstants;
import com.gemslibe.instastats.instagram.exceptions.InstagramException;
import com.gemslibe.instastats.instagram.interfaces.InstagramAPIResponseCallback;
import com.gemslibe.instastats.instagram.interfaces.InstagramLoginCallbackListener;
import com.gemslibe.instastats.instagram.objects.IGPagInfo;
import com.gemslibe.instastats.instagram.objects.IGSession;
import com.gemslibe.instastats.instagram.objects.IGUser;
import com.gemslibe.instastats.instagram.utils.InstagramKitLoginScope;
import com.gemslibe.instastats.instagram.widgets.InstagramLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.btnLogin)
    protected InstagramLoginButton instagramLoginButton;
    String[] scopes = {
            InstagramKitLoginScope.BASIC,
            InstagramKitLoginScope.COMMENTS,
            InstagramKitLoginScope.LIKES,
            InstagramKitLoginScope.RELATIONSHIP,
            InstagramKitLoginScope.PUBLIC_ACCESS,
            InstagramKitLoginScope.FOLLOWER_LIST};
    View.OnClickListener instagramOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, InstagramAuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);

            intent.putExtra(InstagramEngine.TYPE, InstagramEngine.TYPE_LOGIN);
            intent.putExtra(InstagramEngine.SCOPE, scopes);

            startActivityForResult(intent, InstagramEngine.REQUEST_CODE_LOGIN);
        }
    };
    InstagramAPIResponseCallback<IGUser> instagramUserResponseCallback = new InstagramAPIResponseCallback<IGUser>() {
        @Override
        public void onResponse(IGUser responseObject, IGPagInfo pageInfo) {
            Log.v("SampleActivity", "User:" + responseObject.getUsername() + ", User Id: " + responseObject.getId());

            Toast.makeText(LoginActivity.this, "Username: " + responseObject.getUsername(),
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(InstagramException exception) {
            Log.v("SampleActivity", "Exception:" + exception.getMessage());
        }
    };
    InstagramLoginCallbackListener instagramLoginCallbackListener = new InstagramLoginCallbackListener() {
        @Override
        public void onSuccess(IGSession session) {

            Toast.makeText(LoginActivity.this, "Wow!!! User trusts you :) " + session.getAccessToken(),
                    Toast.LENGTH_LONG).show();
            InstagramEngine.getInstance(LoginActivity.this).getUserDetails(instagramUserResponseCallback);

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "Oh Crap!!! Canceled.",
                    Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(InstagramException error) {
            Toast.makeText(LoginActivity.this, "User does not trust you :(\n " + error.getMessage(),
                    Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        instagramLoginButton.setOnClickListener(instagramOnClickListener);
        instagramLoginButton.setInstagramLoginCallback(instagramLoginCallbackListener);
        instagramLoginButton.setScopes(scopes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case InstagramEngine.REQUEST_CODE_LOGIN:

                if (resultCode == RESULT_OK) {

                    Bundle bundle = data.getExtras();

                    if (bundle.containsKey(InstagramKitConstants.kSessionKey)) {

                        IGSession session = (IGSession) bundle.getSerializable(InstagramKitConstants.kSessionKey);

                        Toast.makeText(this, "Woohooo!!! User trusts you :) " + session.getAccessToken(),
                                Toast.LENGTH_LONG).show();

                    }
                }
                break;
            case InstagramEngine.REQUEST_CODE_LOGOUT:
                if (resultCode == RESULT_OK) {

                    Toast.makeText(this, "Logged Out Successfully.",
                            Toast.LENGTH_LONG).show();
                }
            default:
                break;
        }

    }

}
