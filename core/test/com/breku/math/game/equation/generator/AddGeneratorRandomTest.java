package com.breku.math.game.equation.generator;

import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.level.LevelDifficulty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by brekol on 15.10.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddGeneratorRandomTest {


    private static final int NUMBER_OF_TRIES = 10000;
    @Spy
    private RandomProvider randomProvider;
    @InjectMocks
    private AddGenerator uut;

    @Test
    public void shouldGenerateCorrectEquation() {
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            for (int i = 0; i < NUMBER_OF_TRIES; i++) {
                final MathEquation mathEquation = uut.generateCorrectEquation(levelDifficulty);
                assertThat(mathEquation.isCorrect()).isTrue();
            }
        }
    }

    @Test
    public void shouldGenerateIncorrectEquation() {
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            for (int i = 0; i < NUMBER_OF_TRIES; i++) {
                final MathEquation mathEquation = uut.generateCorrectEquation(levelDifficulty);
                final MathEquation result = uut.makeResultIncorrectFor(mathEquation, levelDifficulty);
                assertThat(result.isCorrect()).isFalse();
            }
        }
    }


}
