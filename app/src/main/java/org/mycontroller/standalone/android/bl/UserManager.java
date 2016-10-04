package org.mycontroller.standalone.android.bl;

import com.google.gson.Gson;
import org.mycontroller.standalone.android.bl.api.ApiManager;
import org.mycontroller.standalone.android.bl.api.RetrofitCallback;
import org.mycontroller.standalone.android.bl.api.RetrofitResponse;
import org.mycontroller.standalone.android.bo.Login;
import org.mycontroller.standalone.android.bo.request.LoginRequest;
import org.mycontroller.standalone.android.util.GenericCallback;
import org.mycontroller.standalone.android.util.LOG;

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
