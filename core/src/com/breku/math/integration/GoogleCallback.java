package com.breku.math.integration;

/**
 * Created by brekol on 13.10.16.
 */
public interface GoogleCallback {

    void onSucces();

    void onFailure();

    IntegrationCallbackModel getIntegrationCallbackModel();

    void setIntegrationCallbackModel(IntegrationCallbackModel callbackModel);

}
