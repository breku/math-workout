package com.breku.math.game.equation.generator;

/**
 * Created by brekol on 16.10.16.
 */
public class DivGeneratorTest extends AbstractGeneratorRandomTest {

    @Override
    protected AbstractEquationGenerator getAbstractEquationGenerator() {
        return new DivGenerator();
    }
}