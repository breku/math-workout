package com.breku.math.game.level;

/**
 * Created by brekol on 15.10.16.
 */
public enum LevelDifficulty {
    EASY(20, false),
    MEDIUM(100, false),
    HARD(100, true);

    private final Integer randomSeedSize;
    private final boolean minusAllowed;

    private LevelDifficulty(Integer randomSeedSize, Boolean minusAllowed) {
        this.randomSeedSize = randomSeedSize;
        this.minusAllowed = minusAllowed;
    }

    public Integer getRandomSeedSize() {
        return randomSeedSize;
    }

    public boolean isMinusAllowed() {
        return minusAllowed;
    }


}
