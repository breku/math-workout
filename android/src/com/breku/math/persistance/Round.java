package com.breku.math.persistance;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brekol on 26.10.16.
 */
public class Round {

    private Map<String, Integer> scoreMap = new HashMap<>();

    public Map<String, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }
}
