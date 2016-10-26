package com.breku.math.integration;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 17.10.16.
 */
public class GameIntegrationCallbackValue {
    private int turnCounter;
    private int score;
    private LevelDifficulty levelDifficulty;
    private GameType gameType;
    private boolean shouldSetGameType;
    public GameIntegrationCallbackValue(LevelDifficulty levelDifficulty, GameType gameType) {
        this.levelDifficulty = levelDifficulty;
        this.gameType = gameType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isShouldSetGameType() {
        return shouldSetGameType;
    }

    public void setShouldSetGameType(boolean shouldSetGameType) {
        this.shouldSetGameType = shouldSetGameType;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
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
}
