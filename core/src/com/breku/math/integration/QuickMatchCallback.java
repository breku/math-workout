package com.breku.math.integration;

import com.breku.math.screen.ScreenType;
import com.breku.math.stage.AbstractStage;

import static com.breku.math.configuration.ContextConstants.*;

/**
 * Created by brekol on 13.10.16.
 */
public class QuickMatchCallback implements LaunchCallback {

    private final AbstractStage stage;
    private IntegrationCallbackModel callbackModel;

    public QuickMatchCallback(AbstractStage stage, IntegrationCallbackModel callbackModel) {
        this.stage = stage;
        this.callbackModel = callbackModel;
    }

    @Override
    public void onSucces() {
        if (stage.getAdditionalDataValue(ADDITIONAL_DATA_CALLBACK_SHOULD_FAIL) == null ||
                !(boolean) stage.getAdditionalDataValue(ADDITIONAL_DATA_CALLBACK_SHOULD_FAIL)) {
            stage.addAdditionalData(ADDITIONAL_DATA_GAME_TYPE_KEY, callbackModel.getGameType());
            stage.addAdditionalData(ADDITIONAL_DATA_LEVEL_DIFFICULTY_KEY, callbackModel.getLevelDifficulty());
            stage.setTargetScreenType(ScreenType.GAME);
        }
    }

    @Override
    public void onFailure() {

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
