package com.breku.math.endgame;

import com.breku.math.configuration.ContextConstants;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.integration.TakeTurnIntegrationCallbackValue;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 16.10.16.
 */
public class EndGameStage extends AbstractStage {

    private int score;

    public EndGameStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        score = (int) getAdditionalDataValue(ContextConstants.ADDITIONAL_DATA_GAME_SCORE);
    }

    @Override
    public void disposeStage() {

    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        font.draw(getBatch(), "Score: " + score, 500, 600);
        font.draw(getBatch(), "Click anywhere to get to menu", 500, 500);
        getBatch().end();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        googleApiService.takeTurn(new TakeTurnCallback(new TakeTurnIntegrationCallbackValue(score)));
        setTargetScreenType(ScreenType.MENU);
        return true;
    }
}
