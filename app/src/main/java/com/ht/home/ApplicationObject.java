package com.ht.home;

import android.app.Application;
import android.content.Context;

import com.ht.home.bl.PreferencesManager;
import com.ht.home.bl.api.ApiManager;
import com.orm.SugarContext;

/**
 * Created by tibi on 26/05/16.
 */
public class ApplicationObject extends Application {

    private static Context mContext;
    private static ApplicationObject instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        mContext = getApplicationContext();

        SugarContext.init(mContext);

        createAPI();
    }

    public static Context getContext() {
        return mContext;
    }
    public static ApplicationObject getApplication() {
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public void createAPI() {
        if (PreferencesManager.getInstance().getHost()!=null)
            ApiManager.getInstance().createApi();
    }
}
