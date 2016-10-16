package com.breku.math.game.equation.generator;

import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.equation.MathParameter;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 16.10.16.
 */
public class DivGenerator extends AbstractEquationGenerator {

    @Override
    protected MathEquation generateCorrectEquation(LevelDifficulty levelDifficulty) {
        MathEquation mathEquation = new MathEquation();
        mathEquation.setMathParameter(MathParameter.DIV);

        if (levelDifficulty.isMinusAllowed()) {
            while (mathEquation.getResult() == null || Math.abs(mathEquation.getResult()) > levelDifficulty.getRandomSeedSize() ||
                    mathEquation.getX() % mathEquation.getY() != 0) {
                mathEquation.setX(randomProvider.nextInt() % levelDifficulty.getRandomSeedSize());
                mathEquation.setY(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()) + 1);
                mathEquation.setResult(mathEquation.getX() / mathEquation.getY());
            }
        } else {
            while (mathEquation.getResult() == null || Math.abs(mathEquation.getResult()) > levelDifficulty.getRandomSeedSize() ||
                    mathEquation.getResult() < 0 || mathEquation.getX() % mathEquation.getY() != 0) {
                mathEquation.setX(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()));
                mathEquation.setY(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()) + 1);
                mathEquation.setResult(mathEquation.getX() / mathEquation.getY());
            }
        }

        return mathEquation;

    }
}
