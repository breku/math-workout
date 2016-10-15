package com.breku.math.game.equation.generator;

import org.mockito.InjectMocks;

/**
 * Created by brekol on 15.10.16.
 */
public class SubGeneratorRandomTest extends AbstractGeneratorRandomTest {

    @InjectMocks
    private SubGenerator uut;

    @Override
    protected AbstractEquationGenerator getAbstractEquationGenerator() {
        return uut;
    }
}