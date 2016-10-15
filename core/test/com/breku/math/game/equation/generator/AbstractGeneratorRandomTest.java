package com.breku.math.game.equation.generator;

import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.level.LevelDifficulty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by brekol on 15.10.16.
 */

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractGeneratorRandomTest {


    private static final int NUMBER_OF_TRIES = 10000;
    @Spy
    private RandomProvider randomProvider;

    @Test
    public void shouldGenerateCorrectEquation() {
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            for (int i = 0; i < NUMBER_OF_TRIES; i++) {
                final MathEquation mathEquation = getAbstractEquationGenerator().generateCorrectEquation(levelDifficulty);
                assertThat(mathEquation.isCorrect()).isTrue();
            }
        }
    }

    protected abstract AbstractEquationGenerator getAbstractEquationGenerator();

    @Test
    public void shouldGenerateIncorrectEquation() {
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            for (int i = 0; i < NUMBER_OF_TRIES; i++) {
                final MathEquation mathEquation = getAbstractEquationGenerator().generateCorrectEquation(levelDifficulty);
                final MathEquation result = getAbstractEquationGenerator().makeResultIncorrectFor(mathEquation, levelDifficulty);
                assertThat(result.isCorrect()).isFalse();
            }
        }
    }
}
