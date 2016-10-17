package com.breku.math.game.level;

import com.breku.math.game.equation.generator.*;

/**
 * Created by brekol on 15.10.16.
 */
public enum GameType {

    ADD(new AddGenerator()),
    SUB(new SubGenerator()),
    MUL(new MulGenerator()),
    DIV(new DivGenerator()),
    ALL(new AddGenerator(), new SubGenerator(), new MulGenerator(), new DivGenerator());


    private final AbstractEquationGenerator[] generators;

    GameType(AbstractEquationGenerator... generators) {
        this.generators = generators;
    }

    public AbstractEquationGenerator[] getGenerators() {
        return generators;
    }
}
