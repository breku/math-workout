package com.breku.math.game.equation.generator;

import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.equation.MathParameter;
import com.breku.math.game.level.LevelDifficulty;

/**
 * Created by brekol on 15.10.16.
 */
public class AddGenerator extends AbstractEquationGenerator {

    @Override
    public MathEquation generateRandomEquation(LevelDifficulty levelDifficulty) {
        final MathEquation mathEquation = generateCorrectEquation(levelDifficulty);
        if (shouldMakeCorrectEquation()) {
            return makeResultIncorrectFor(mathEquation, levelDifficulty);
        }
        return mathEquation;
    }


    @Override
    protected MathEquation generateCorrectEquation(LevelDifficulty levelDifficulty) {
        final MathEquation mathEquation = new MathEquation();
        mathEquation.setMathParameter(MathParameter.ADD);

        if (levelDifficulty.isMinusAllowed()) {
            while (mathEquation.getResult() == null || Math.abs(mathEquation.getResult()) > levelDifficulty.getRandomSeedSize()) {
                mathEquation.setX(randomProvider.nextInt() % levelDifficulty.getRandomSeedSize());
                mathEquation.setY(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()));
                mathEquation.setResult(mathEquation.getX() + mathEquation.getY());
            }
        } else {
            while (mathEquation.getResult() == null || Math.abs(mathEquation.getResult()) > levelDifficulty.getRandomSeedSize()) {
                mathEquation.setX(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()));
                mathEquation.setY(randomProvider.nextInt(levelDifficulty.getRandomSeedSize()));
                mathEquation.setResult(mathEquation.getX() + mathEquation.getY());
            }
        }

        return mathEquation;
    }
}
