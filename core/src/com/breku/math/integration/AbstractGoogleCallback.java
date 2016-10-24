package com.breku.math.integration;

/**
 * Created by brekol on 24.10.16.
 */
public abstract class AbstractGoogleCallback<T> implements GoogleCallback<T> {

    protected T callbackModel;

    public AbstractGoogleCallback() {
        // default one
    }

    public AbstractGoogleCallback(T callbackModel) {
        this.callbackModel = callbackModel;
    }

    @Override
    public T getCallbackValue() {
        return callbackModel;
    }

    @Override
    public void setCallbackValue(T callbackModel) {
        this.callbackModel = callbackModel;
    }
}
