package com.breku.math.integration;

/**
 * Created by brekol on 13.10.16.
 */
public interface GoogleCallback<T> {

    void onSucces();

    void onFailure();

    T getCallbackValue();

    void setCallbackValue(T callbackModel);

}
