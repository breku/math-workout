package com.breku.math.game.equation.generator;

import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.level.LevelDifficulty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by brekol on 15.10.16.
 */
@RunWith(Parameterized.class)
public class AddGeneratorTest {

    @Parameterized.Parameter(value = 0)
    public int x;
    @Parameterized.Parameter(value = 1)
    public int y;
    @Parameterized.Parameter(value = 2)
    public int expectedResult;
    @Mock
    private RandomProvider randomProvider;
    @InjectMocks
    private AddGenerator uut;

    @Parameterized.Parameters(name = "{index}: testAdd {0}+{1}={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {3, 2, 5},
                {0, 0, 0},
                {0, 1, 1},
                {1, 0, 1},
                {10, 10, 20}
        });
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        when(randomProvider.nextInt(anyInt())).thenReturn(x).thenReturn(y);

        // when
        final MathEquation mathEquation = uut.generateCorrectEquation(LevelDifficulty.EASY);

        // then
        assertThat(mathEquation.getResult()).isEqualTo(expectedResult);
    }

}