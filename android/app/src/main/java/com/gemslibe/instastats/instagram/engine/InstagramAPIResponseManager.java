package com.gemslibe.instastats.instagram.engine;

import com.gemslibe.instastats.instagram.exceptions.InstagramException;
import com.gemslibe.instastats.instagram.interfaces.InstagramAPIResponseCallback;
import com.gemslibe.instastats.instagram.objects.IGAPIResponse;
import com.gemslibe.instastats.instagram.objects.IGMeta;
import com.gemslibe.instastats.instagram.objects.IGPagInfo;
import com.google.gson.Gson;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sayyam on 3/18/16.
 */
public class InstagramAPIResponseManager<T> implements Callback<IGAPIResponse> {

    InstagramAPIResponseCallback<T> instagramAPIResponseCallback;
    private Type type;

    public InstagramAPIResponseManager(InstagramAPIResponseCallback instagramAPIResponseCallback, Type type) {
        this.instagramAPIResponseCallback = instagramAPIResponseCallback;
        this.type = type;
    }

    public Type getMyType() {
        return this.type;
    }

    @Override
    public void onFailure(Call<IGAPIResponse> call, Throwable t) {
        instagramAPIResponseCallback.onFailure(new InstagramException(t.getMessage()));
    }

    @Override
    public void onResponse(Call<IGAPIResponse> call, Response<IGAPIResponse> response) {

        try {

            if (response.code() == 200) {

                String data = new Gson().toJson(response.body().getData());

                IGPagInfo pagination = response.body().getPagination();

                if (null != data && !data.isEmpty()) {

                    T responseData = new Gson().fromJson(data, getMyType());
                    //T responseData = new Gson().fromJson(data, new TypeToken<T>(){}.getType());

                    instagramAPIResponseCallback.onResponse(responseData, pagination);
                } else {
                    instagramAPIResponseCallback.onFailure(new InstagramException("Invalid Response"));
                }
            } else {

                if (null != response.errorBody()) {

                    String stringMeta = response.errorBody().string();

                    IGAPIResponse apiResponse = new Gson().fromJson(stringMeta, IGAPIResponse.class);
                    IGMeta meta = apiResponse.getMeta();

                    if (null != meta) {
                        instagramAPIResponseCallback.onFailure(new InstagramException(meta.getErrorType(), meta.getErrorMessage()));

                    } else {
                        instagramAPIResponseCallback.onFailure(new InstagramException("Usually it does not happen but when it does, try again."));
                    }
                } else {
                    instagramAPIResponseCallback.onFailure(new InstagramException("Usually it does not happen but when it does,try again."));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            instagramAPIResponseCallback.onFailure(new InstagramException("Invalid Response, please check call parameters."));
        }

    }

}
