package com.breku.math.game.equation.generator;

import org.mockito.InjectMocks;

/**
 * Created by brekol on 15.10.16.
 */
public class AddGeneratorRandomTest extends AbstractGeneratorRandomTest {

    @InjectMocks
    private AddGenerator uut;

    @Override
    protected AbstractEquationGenerator getAbstractEquationGenerator() {
        return uut;
    }
}
