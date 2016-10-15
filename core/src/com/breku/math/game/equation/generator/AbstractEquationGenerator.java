package com.breku.math.game.equation.generator;

import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 15.10.16.
 */
public abstract class AbstractEquationGenerator {

    protected RandomProvider randomProvider = new RandomProvider();

    protected boolean shouldMakeCorrectEquation() {
        return randomProvider.nextInt(100) > 50;
    }

    protected MathEquation makeResultIncorrectFor(MathEquation mathEquation, LevelDifficulty levelDifficulty) {
        while (mathEquation.isCorrect() || !mathEquation.hasValidParameters()) {
            mathEquation = generateCorrectEquation(levelDifficulty);
            mathEquation.setY(randomProvider.nextInt(Math.abs(mathEquation.getY() + 1)));
        }

        return mathEquation;
    }


    public abstract MathEquation generateRandomEquation(LevelDifficulty levelDifficulty);

    protected abstract MathEquation generateCorrectEquation(LevelDifficulty levelDifficulty);
}
