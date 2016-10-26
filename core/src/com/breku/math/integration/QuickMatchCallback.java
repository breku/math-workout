package com.breku.math.integration;

import com.badlogic.gdx.Gdx;
import com.breku.math.screen.ScreenType;
import com.breku.math.stage.AbstractStage;

import static com.breku.math.configuration.ContextConstants.*;

/**
 * Created by brekol on 13.10.16.
 */
public class QuickMatchCallback extends AbstractGoogleCallback<GameIntegrationCallbackValue> {

    private static final String TAG = "QuickMatchCallback";

    private final AbstractStage stage;

    public QuickMatchCallback(AbstractStage stage, GameIntegrationCallbackValue callbackModel) {
        super(callbackModel);
        this.stage = stage;
    }

    @Override
    public void onSucces() {
        Gdx.app.log(TAG, String.format("onSuccess with callbackModel=[%s]", callbackModel));
        if (stage.getAdditionalDataValue(ADDITIONAL_DATA_CALLBACK_SHOULD_FAIL) == null ||
                !(boolean) stage.getAdditionalDataValue(ADDITIONAL_DATA_CALLBACK_SHOULD_FAIL)) {
            stage.addAdditionalData(ADDITIONAL_DATA_GAME_TYPE_KEY, callbackModel.getGameType());
            stage.addAdditionalData(ADDITIONAL_DATA_LEVEL_DIFFICULTY_KEY, callbackModel.getLevelDifficulty());

            if (callbackModel.isShouldSetGameType() && callbackModel.getGameType() == null) {
                stage.addAdditionalData(ADDITIONAL_DATA_SET_GAME_TYPE, true);
            }

            stage.setTargetScreenType(ScreenType.LOADING_FINISHED);
        }
    }

    @Override
    public void onFailure() {
        Gdx.app.error(TAG, "onFailure");
        stage.setTargetScreenType(ScreenType.MENU);
    }


}
