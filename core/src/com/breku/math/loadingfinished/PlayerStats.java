package com.breku.math.loadingfinished;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brekol on 27.10.16.
 */
public class PlayerStats {

    private String name = "?";
    private String participantId;
    private List<Integer> scoreList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public List<Integer> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Integer> scoreList) {
        this.scoreList = scoreList;
    }
}
