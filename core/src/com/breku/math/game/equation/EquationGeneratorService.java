package com.breku.math.game.equation;

import com.breku.math.game.equation.generator.AbstractEquationGenerator;
import com.breku.math.game.equation.generator.AddGenerator;
import com.breku.math.game.level.LevelDifficulty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brekol on 13.10.16.
 */
public class EquationGeneratorService {

    private final AbstractEquationGenerator addGenerator = new AddGenerator();

    public List<MathEquation> generateEquations(LevelDifficulty levelDifficulty) {
        final List<MathEquation> result = new ArrayList<>();

        final MathEquation mathEquation = addGenerator.generateRandomEquation(levelDifficulty);

        return result;
    }


}
