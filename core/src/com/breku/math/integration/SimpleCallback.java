package com.breku.math.integration;

import com.badlogic.gdx.Gdx;

/**
 * Created by brekol on 27.10.16.
 */
public class SimpleCallback extends AbstractGoogleCallback<GameIntegrationCallbackValue> {

    public SimpleCallback(GameIntegrationCallbackValue callbackModel) {
        super(callbackModel);
    }

    private static final String TAG = "SimpleCallback";

    @Override
    public void onSucces() {
        Gdx.app.debug(TAG, String.format("onSucces with callbackValue=[%s]", callbackModel));
    }

    @Override
    public void onFailure() {

    }
}
