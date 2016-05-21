package com.gemslibe.instastats.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemslibe.instastats.R;
import com.gemslibe.instastats.R;
import com.gemslibe.instastats.databinding.DrawerHeaderBinding;
import com.gemslibe.instastats.model.UserModel;
import com.gemslibe.instastats.mvp.presenters.DrawerHeaderPresenter;
import com.gemslibe.instastats.mvp.views.DrawerHeaderView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kuzya on 21.05.2016.
 */

public class DrawerHeaderLayout extends RelativeLayout implements DrawerHeaderView {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.imgUserAvatar)
    ImageView imgUserAvatar;
    @Bind(R.id.txtFollowedCountName)
    TextView txtFollowedCountName;
    @Bind(R.id.txtFollowedCount)
    TextView txtFollowedCount;

    @Bind(R.id.txtMediaCountName)
    TextView txtMediaCountName;
    @Bind(R.id.txtMediaCount)
    TextView txtMediaCount;

    @Bind(R.id.txtFollowsCountName)
    TextView txtFollowsCountName;
    @Bind(R.id.txtFollowsCount)
    TextView txtFollowsCount;
    private DrawerHeaderBinding binding;

    public DrawerHeaderLayout(Context context) {
        super(context);
    }

    public DrawerHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawerHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ButterKnife.bind(this);
        binding = DrawerHeaderBinding.bind(this);
    }

    @Override
    public void showProgress(boolean inProgress) {
        progressBar.setVisibility(inProgress?VISIBLE:GONE);
        imgUserAvatar.setVisibility(!inProgress?VISIBLE:GONE);

        txtMediaCount.setVisibility(!inProgress?VISIBLE:INVISIBLE);
        txtMediaCountName.setVisibility(!inProgress?VISIBLE:INVISIBLE);

        txtFollowsCount.setVisibility(!inProgress?VISIBLE:INVISIBLE);
        txtFollowsCountName.setVisibility(!inProgress?VISIBLE:INVISIBLE);

        txtFollowedCountName.setVisibility(!inProgress?VISIBLE:INVISIBLE);
        txtFollowedCount.setVisibility(!inProgress?VISIBLE:INVISIBLE);
    }

    @Override
    public void setUserModel(UserModel userModel) {
        binding.setViewModel(userModel);
    }
}
