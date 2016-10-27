package com.breku.math.endgame;

import com.breku.math.configuration.ContextConstants;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.SimpleCallback;
import com.breku.math.integration.persistance.TurnData;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brekol on 16.10.16.
 */
public class EndGameStage extends AbstractStage {

    private int score;
    private TurnData turnData;
    private LevelDifficulty levelDifficulty;
    private GameType gameType;
    private Map<String, Object> additionalDataCopy;

    public EndGameStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        score = (int) getAdditionalDataValue(ContextConstants.ADDITIONAL_DATA_GAME_SCORE);
        turnData = (TurnData) getAdditionalDataValue(ContextConstants.ADDITIONAL_DATA_TURN_DATA);
        levelDifficulty = (LevelDifficulty) getAdditionalDataValue(ContextConstants.ADDITIONAL_DATA_LEVEL_DIFFICULTY_KEY);
        gameType = (GameType) getAdditionalDataValue(ContextConstants.ADDITIONAL_DATA_GAME_TYPE_KEY);
        additionalDataCopy = new HashMap<>(getAdditionalData());

    }

    @Override
    public void disposeStage() {

    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        font.draw(getBatch(), "Score: " + score, 500, 600);
        if (turnData.getTurnCounter() % 2 != 0) {
            font.draw(getBatch(), "Nice, now you are going to choose", 500, 500);
        } else {
            font.draw(getBatch(), "Well played. Now your opponent is going to play", 500, 500);
        }

        getBatch().end();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        final GameIntegrationCallbackValue callbackValue = new GameIntegrationCallbackValue(levelDifficulty, gameType);
        callbackValue.setScore(score);

        if (turnData.getTurnCounter() % 2 != 0) {
            setTargetScreenType(ScreenType.LOADING);
            googleApiService.takeTurnAsMyself(new TakeTurnCallback(callbackValue, this), true);
        } else {
            setTargetScreenType(ScreenType.MENU);
            googleApiService.takeTurn(new SimpleCallback(callbackValue));
        }

        return true;
    }
}
