package com.breku.math.endgame;

import com.badlogic.gdx.Gdx;
import com.breku.math.integration.AbstractGoogleCallback;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.integration.TakeTurnIntegrationCallbackValue;

/**
 * Created by brekol on 24.10.16.
 */
public class TakeTurnCallback extends AbstractGoogleCallback<TakeTurnIntegrationCallbackValue> {

    private static final String TAG = "TakeTurnCallback";

    public TakeTurnCallback(TakeTurnIntegrationCallbackValue callbackModel) {
        super(callbackModel);
    }

    @Override
    public void onSucces() {
        Gdx.app.log(TAG, "onSucces");
    }

    @Override
    public void onFailure() {

    }

}
