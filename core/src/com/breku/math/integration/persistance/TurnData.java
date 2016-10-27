package com.breku.math.integration.persistance;

import com.breku.math.loadingfinished.LevelAndGameTypeStats;

import java.util.*;

/**
 * Created by brekol on 25.10.16.
 */
public class TurnData {

    private String data = "";
    private boolean firstRound = true;
    private int turnCounter;
    private Map<String, String> playersNameMap = new HashMap<>();
    private List<Round> roundList = Arrays.asList(new Round(), new Round(), new Round(), new Round());


    public List<Integer> getScoreFor(final String participantId) {
        final List<Integer> result = new ArrayList<>();
        for (Round round : roundList) {
            final Integer score = round.getScoreMap().get(participantId);
            if (score != null) {
                result.add(score);
            }
        }
        return result;
    }

    public List<LevelAndGameTypeStats> getLevelAndGameTypeStats() {
        final List<LevelAndGameTypeStats> result = new ArrayList<>();
        for (Round round : roundList) {
            if (round.getGameType() != null && round.getLevelDifficulty() != null) {
                result.add(new LevelAndGameTypeStats(round.getLevelDifficulty(), round.getGameType()));
            }
        }
        return result;

    }

    public Map<String, String> getPlayersNameMap() {
        return playersNameMap;
    }

    public void setPlayersNameMap(Map<String, String> playersNameMap) {
        this.playersNameMap = playersNameMap;
    }

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
