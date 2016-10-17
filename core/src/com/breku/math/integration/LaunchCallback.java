package com.breku.math.integration;

import java.io.Serializable;

/**
 * Created by brekol on 13.10.16.
 */
public interface LaunchCallback {

    void onSucces();

    void onFailure();

    IntegrationCallbackModel getIntegrationCallbackModel();

    void setIntegrationCallbackModel(IntegrationCallbackModel callbackModel);

}
