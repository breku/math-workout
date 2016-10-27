package com.breku.math.integration;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;
import com.breku.math.integration.persistance.TurnData;

/**
 * Created by brekol on 17.10.16.
 */
public class GameIntegrationCallbackValue {
    private Integer score;
    private LevelDifficulty levelDifficulty;
    private GameType gameType;
    private boolean shouldSetGameType;
    private TurnData turnData;

    public GameIntegrationCallbackValue(LevelDifficulty levelDifficulty, GameType gameType) {
        this.levelDifficulty = levelDifficulty;
        this.gameType = gameType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isShouldSetGameType() {
        return shouldSetGameType;
    }

    public void setShouldSetGameType(boolean shouldSetGameType) {
        this.shouldSetGameType = shouldSetGameType;
    }

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

    public TurnData getTurnData() {
        return turnData;
    }

    public void setTurnData(TurnData turnData) {
        this.turnData = turnData;
    }
}
