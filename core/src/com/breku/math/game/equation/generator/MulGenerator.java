package com.breku.math.game.equation.generator;

import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.equation.MathParameter;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 16.10.16.
 */
public class MulGenerator extends AbstractEquationGenerator {

    @Override
    protected MathEquation generateCorrectEquation(LevelDifficulty levelDifficulty) {
        MathEquation mathEquation = new MathEquation();
        mathEquation.setMathParameter(MathParameter.MUL);

        if (levelDifficulty.isMinusAllowed()) {
            while (mathEquation.getResult() == null || Math.abs(mathEquation.getResult()) > levelDifficulty.getRandomSeedSize()) {
                mathEquation.setX(randomProvider.nextInt() % levelDifficulty.getRandomSeedSize());
                mathEquation.setY(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()));
                mathEquation.setResult(mathEquation.getX() * mathEquation.getY());
            }
        } else {
            while (mathEquation.getResult() == null || Math.abs(mathEquation.getResult()) > levelDifficulty.getRandomSeedSize() ||
                    mathEquation.getResult() < 0) {
                mathEquation.setX(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()));
                mathEquation.setY(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()));
                mathEquation.setResult(mathEquation.getX() * mathEquation.getY());
            }
        }

        return mathEquation;

    }
}
