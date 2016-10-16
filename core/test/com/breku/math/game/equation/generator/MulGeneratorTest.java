package com.breku.math.game.equation.generator;

/**
 * Created by brekol on 16.10.16.
 */
public class MulGeneratorTest extends AbstractGeneratorRandomTest {

    @Override
    protected AbstractEquationGenerator getAbstractEquationGenerator() {
        return new MulGenerator();
    }
}