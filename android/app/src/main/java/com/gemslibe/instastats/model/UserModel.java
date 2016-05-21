package com.gemslibe.instastats.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by kuzya on 21.05.2016.
 */
//TODO use database framework
public class UserModel extends BaseObservable {

    private String mImageUrl;
    private Integer mMediaCount;
    private Integer mFollowsCount;
    private Integer mFollowedCount;
    private String mUserName;

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public void setMediaCount(Integer mediaCount) {
        mMediaCount = mediaCount;
    }

    public void setFollowsCount(Integer followsCount) {
        mFollowsCount = followsCount;
    }

    public void setFollowedCount(Integer followedCount) {
        mFollowedCount = followedCount;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Bindable
    public Integer getMediaCount() {
        return mMediaCount;
    }

    @Bindable
    public Integer getFollowsCount() {
        return mFollowsCount;
    }

    @Bindable
    public Integer getFollowedCount() {
        return mFollowedCount;
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }
}
