package com.breku.math.integration;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 17.10.16.
 */
public class GameIntegrationCallbackValue {

    private LevelDifficulty levelDifficulty;
    private GameType gameType;
    private boolean shouldSetGameType;

    public GameIntegrationCallbackValue(LevelDifficulty levelDifficulty, GameType gameType) {
        this.levelDifficulty = levelDifficulty;
        this.gameType = gameType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameIntegrationCallbackValue{");
        sb.append("levelDifficulty=").append(levelDifficulty);
        sb.append(", gameType=").append(gameType);
        sb.append(", shouldSetGameType=").append(shouldSetGameType);
        sb.append('}');
        return sb.toString();
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
}
