package com.breku.math.loadingfinished;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 27.10.16.
 */
public class LevelAndGameTypeStats {
    private LevelDifficulty levelDifficulty;
    private GameType gameType;

    public LevelAndGameTypeStats(LevelDifficulty levelDifficulty, GameType gameType) {
        this.levelDifficulty = levelDifficulty;
        this.gameType = gameType;
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
