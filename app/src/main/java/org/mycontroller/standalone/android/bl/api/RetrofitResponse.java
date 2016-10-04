package org.mycontroller.standalone.android.bl.api;

/**
 * Created by Maurice Wu on 1/19/16.
 */
public interface RetrofitResponse<T>
{
    void onSuccess(T response);

    void onFailure(String error);
}
