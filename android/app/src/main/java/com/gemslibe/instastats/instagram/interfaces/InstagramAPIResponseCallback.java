package com.gemslibe.instastats.instagram.interfaces;

import com.gemslibe.instastats.instagram.exceptions.InstagramException;
import com.gemslibe.instastats.instagram.objects.IGPagInfo;

/**
 * Created by Sayyam on 3/18/16.
 */
public interface InstagramAPIResponseCallback<T> {

    public void onResponse(T responseObject, IGPagInfo pageInfo);

    public void onFailure(InstagramException exception);
}
