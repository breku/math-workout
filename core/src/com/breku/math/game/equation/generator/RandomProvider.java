package com.breku.math.game.equation.generator;

import java.util.Random;

/**
 * Created by brekol on 15.10.16.
 */
public class RandomProvider {

    private Random random = new Random();

    public Integer nextInt(int bound) {
        return random.nextInt(bound);
    }

    public Integer nextInt() {
        return random.nextInt();
    }
}
