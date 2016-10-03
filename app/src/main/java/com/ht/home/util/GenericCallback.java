package com.ht.home.util;

/**
 * Created by tibi on 28/09/16.
 */
public interface GenericCallback<T> {
    void onSuccess(T response);

    void onError(String error);
}
