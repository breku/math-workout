package com.breku.math.persistance;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 25.10.16.
 */
public class TurnData {

    private String data = "";
    private int turnCounter;
    private LevelDifficulty levelDifficulty;
    private GameType gameType;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
