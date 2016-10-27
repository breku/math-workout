package com.breku.math.endgame;

import com.badlogic.gdx.Gdx;
import com.breku.math.configuration.ContextConstants;
import com.breku.math.integration.AbstractGoogleCallback;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.screen.ScreenType;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 24.10.16.
 */
public class TakeTurnCallback extends AbstractGoogleCallback<GameIntegrationCallbackValue> {

    private static final String TAG = "TakeTurnCallback";

    private final AbstractStage stage;

    public TakeTurnCallback(GameIntegrationCallbackValue callbackModel, AbstractStage stage) {
        super(callbackModel);
        this.stage = stage;
    }

    @Override
    public void onSucces() {
        Gdx.app.log(TAG, String.format("onSucces with callbackModel=[%s]", callbackModel));
        stage.addAdditionalData(ContextConstants.ADDITIONAL_DATA_TURN_DATA, callbackModel.getTurnData());

        if (callbackModel.isShouldSetGameType()) {
            stage.addAdditionalData(ContextConstants.ADDITIONAL_DATA_SET_GAME_TYPE, true);
            stage.setTargetScreenType(ScreenType.GAME_TYPE);
        } else {
            stage.setTargetScreenType(ScreenType.LOADING_FINISHED);
        }
    }

    @Override
    public void onFailure() {

    }

}
