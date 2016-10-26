package com.breku.math.persistance;

import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by brekol on 25.10.16.
 */
public class TurnData {

    private String data = "";
    private boolean firstRound = true;
    private int turnCounter;
    private LevelDifficulty levelDifficulty;
    private GameType gameType;
    private List<Round> roundList = Arrays.asList(new Round(), new Round(), new Round(), new Round());

    public Round getCurrentRound() {
        final int roundIndex = turnCounter / 2;
        return roundList.get(roundIndex);
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

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
