package com.breku.math.integration;

/**
 * Created by brekol on 24.10.16.
 */
public abstract class AbstractGoogleCallback implements GoogleCallback {

    protected IntegrationCallbackModel callbackModel;

    public AbstractGoogleCallback(IntegrationCallbackModel callbackModel) {
        this.callbackModel = callbackModel;
    }

    @Override
    public IntegrationCallbackModel getIntegrationCallbackModel() {
        return callbackModel;
    }

    @Override
    public void setIntegrationCallbackModel(IntegrationCallbackModel callbackModel) {
        this.callbackModel = callbackModel;
    }
}
