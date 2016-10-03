package com.ht.home.bl;

import com.google.gson.Gson;
import com.ht.home.bl.api.ApiManager;
import com.ht.home.bl.api.RetrofitCallback;
import com.ht.home.bl.api.RetrofitResponse;
import com.ht.home.bo.Login;
import com.ht.home.bo.request.LoginRequest;
import com.ht.home.util.GenericCallback;
import com.ht.home.util.LOG;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by tibi on 28/09/16.
 */
public class UserManager {

    private static UserManager instance;

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();

        return instance;
    }

    public void login(String username, String password, final GenericCallback<Void> callback) {
        // Save credential hash
        PreferencesManager.getInstance().saveLoginHash(username, password);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(new LoginRequest(username, password)));
        // Login
        ApiManager.getInstance().API.login(body).enqueue(new RetrofitCallback<>(new RetrofitResponse<Login>() {
            @Override
            public void onSuccess(Login response) {
                LOG.debug("login success: " + response.toString());
                callback.onSuccess(null);
            }

            @Override
            public void onFailure(String error) {
                LOG.debug(error);
                callback.onError(error);
            }
        }));
    }

    public void logout() {
        PreferencesManager.getInstance().removeLoginHash();
    }

    public boolean isLoggedIn() {
        return PreferencesManager.getInstance().hasLoginHash();
    }
}
