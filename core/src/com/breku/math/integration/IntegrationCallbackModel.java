package com.breku.math.integration;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 17.10.16.
 */
public class IntegrationCallbackModel {

    public IntegrationCallbackModel(LevelDifficulty levelDifficulty, GameType gameType) {
        this.levelDifficulty = levelDifficulty;
        this.gameType = gameType;
    }

    private LevelDifficulty levelDifficulty;

    private GameType gameType;

    public LevelDifficulty getLevelDifficulty() {
        return levelDifficulty;
    }

    public void setLevelDifficulty(LevelDifficulty levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
