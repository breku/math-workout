package com.breku.math.game.equation;

import com.breku.math.game.equation.generator.AbstractEquationGenerator;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by brekol on 13.10.16.
 */
public class EquationGeneratorService {

    private static final int NUMBER_OF_EQUATIONS = 500;

    public List<MathEquation> generateEquations(GameType gameType, LevelDifficulty levelDifficulty) {
        final List<MathEquation> result = new ArrayList<>();
        int counterOfEquations = 0;
        while (counterOfEquations < NUMBER_OF_EQUATIONS) {
            for (AbstractEquationGenerator generator : gameType.getGenerators()) {
                final MathEquation mathEquation = generator.generateRandomEquation(levelDifficulty);
                result.add(mathEquation);
            }
            counterOfEquations++;
        }
        Collections.shuffle(result);
        return result;
    }


}
