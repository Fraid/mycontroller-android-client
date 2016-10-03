package com.ht.home.bl.api;

import com.google.gson.Gson;
import com.ht.home.bo.ErrorResponse;
import com.ht.home.util.LOG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallback<T> implements Callback<T> {
    public RetrofitResponse<T> retrofitResponse;
    public RetrofitDefaultResponse retrofitDefaultResponse;

    public RetrofitCallback(RetrofitResponse<T> retrofitResponse) {
        this(retrofitResponse, null);
    }

    public RetrofitCallback(RetrofitResponse<T> retrofitResponse, RetrofitDefaultResponse retrofitDefaultResponse) {
        this.retrofitResponse = retrofitResponse;
        this.retrofitDefaultResponse = retrofitDefaultResponse;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (retrofitDefaultResponse != null) {
            retrofitDefaultResponse.onResponse();
        }

        try {
            if (response.errorBody() != null) {
                String errorJSON = response.errorBody().string();

                LOG.debug("Error Body: " + errorJSON);

                String error = "";
                ErrorResponse errorObj = new Gson().fromJson(errorJSON, ErrorResponse.class);
                if (errorObj.messages != null && errorObj.messages.base != null && errorObj.messages.base.size() > 0) {
                    for (int i = 0; i < errorObj.messages.base.size(); i++) {

                        error += errorObj.messages.base.get(i);
                        if (i != (errorObj.messages.base.size() - 1)) {
                            error += ", ";
                        }

                    }
                } else {
                    error = errorObj.error_description;
                }

                if (retrofitResponse != null) {
                    retrofitResponse.onFailure(error);
                }
            } else {
                if (retrofitResponse != null) {
                    retrofitResponse.onSuccess(response.body());
                }
            }
        } catch (Exception e) {
            if (retrofitResponse != null) {
                retrofitResponse.onFailure(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (retrofitResponse != null && t != null && t.getMessage() != null) {
            retrofitResponse.onFailure(t.getMessage());
        }
    }
}