package com.breku.math.integration.persistance;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brekol on 26.10.16.
 */
public class Round {

    private LevelDifficulty levelDifficulty;
    private GameType gameType;
    private Map<String, Integer> scoreMap = new HashMap<>();

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

    public Map<String, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }
}
