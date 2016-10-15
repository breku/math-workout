package com.breku.math.game.level;

import com.breku.math.game.equation.generator.AbstractEquationGenerator;
import com.breku.math.game.equation.generator.AddGenerator;
import com.breku.math.game.equation.generator.SubGenerator;

/**
 * Created by brekol on 15.10.16.
 */
public enum GameType {


    ADD(new AddGenerator()),
    SUB(new SubGenerator());


    private final AbstractEquationGenerator[] generators;

    GameType(AbstractEquationGenerator... generators) {
        this.generators = generators;
    }

    public AbstractEquationGenerator[] getGenerators() {
        return generators;
    }
}
