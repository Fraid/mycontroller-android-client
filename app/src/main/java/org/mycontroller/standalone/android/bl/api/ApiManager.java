package org.mycontroller.standalone.android.bl.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.mycontroller.standalone.android.Config;
import org.mycontroller.standalone.android.bl.PreferencesManager;
import org.mycontroller.standalone.android.bl.UserManager;
import org.mycontroller.standalone.android.util.LOG;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tibi on 01/07/16.
 */
public class ApiManager {

    private static ApiManager instance = null;

    public MyControllerAPI API;

    public static final int UNAUTHORIZED = 401;

    public static ApiManager getInstance() {
        if (instance == null)
            instance = new ApiManager();

        return instance;
    }

    public void createApi() {
        // Add detailed logging for requests
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (Config.ISDEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        // Add headers to each request
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Config.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Config.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = null;

                        if (PreferencesManager.getInstance().hasLoginHash()) {
                            request = chain.request().newBuilder()
                                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                                    .addHeader("Authorization", "Basic " + PreferencesManager.getInstance().getLoginHash())
                                    .build();
                        } else {
                            request = chain.request().newBuilder()
                                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                                    .addHeader("Authorization", "Basic " + PreferencesManager.getInstance().getLoginHash())
                                    .build();
                        }

                        return chain.proceed(request);
                    }
                })
                .addInterceptor(interceptor)
                .addInterceptor(new  Interceptor(){
                    @Override
                    public Response intercept(Chain chain) throws IOException{

                        Request request = chain.request();
                        Response response = chain.proceed(request);

                        if (response.code()==UNAUTHORIZED) {

                            if (UserManager.getInstance().isLoggedIn()) {
                                LOG.debug("User unauthorized!");
                                UserManager.getInstance().logout();
                            }

                        }

                        return response;

                    }
                })
                .build();

        // Set up Retrofit
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PreferencesManager.getInstance().getHost())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(MyControllerAPI.class);
    }
}
