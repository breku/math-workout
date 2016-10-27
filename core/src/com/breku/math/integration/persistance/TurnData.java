package com.breku.math.integration.persistance;

import java.util.Arrays;
import java.util.List;

/**
 * Created by brekol on 25.10.16.
 */
public class TurnData {

    private String data = "";
    private boolean firstRound = true;
    private int turnCounter;

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

}
