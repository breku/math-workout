package com.breku.math.game;

import com.badlogic.gdx.Input;
import com.breku.math.game.button.AbstractGameButton;
import com.breku.math.game.button.ButtonNo;
import com.breku.math.game.button.ButtonOk;
import com.breku.math.game.equation.EquationGeneratorService;
import com.breku.math.game.equation.MathEquation;
import com.breku.math.game.equation.MathEquationActor;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;
import com.breku.math.game.progress.ProgressCircle;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static com.breku.math.configuration.ContextConstants.ADDITIONAL_DATA_GAME_TYPE_KEY;
import static com.breku.math.configuration.ContextConstants.ADDITIONAL_DATA_LEVEL_DIFFICULTY_KEY;
import static com.breku.math.screen.manager.AssetType.NO_BUTTON_TEXTURE;
import static com.breku.math.screen.manager.AssetType.OK_BUTTON_TEXTURE;

/**
 * Created by brekol on 13.10.16.
 */
public class GameStage extends AbstractStage {

    private AbstractGameButton buttonOk, buttonNo;
    private EquationGeneratorService equationGeneratorService;
    private Queue<MathEquationActor> mathEquationActors;
    private ProgressCircle progressCircle;

    public GameStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        buttonNo = new ButtonNo(assetManagerWrapper.getTexture(NO_BUTTON_TEXTURE));
        buttonOk = new ButtonOk(assetManagerWrapper.getTexture(OK_BUTTON_TEXTURE));
        equationGeneratorService = new EquationGeneratorService();
        progressCircle = new ProgressCircle();
        final List<MathEquation> mathEquations = generateMathEquations();
        mathEquationActors = convertMathEquationsToActors(mathEquations);


        for (final MathEquationActor mathEquationActor : mathEquationActors) {
            addActor(mathEquationActor);
        }
        addActor(progressCircle);
        addActor(buttonNo);
        addActor(buttonOk);
    }


    private List<MathEquation> generateMathEquations() {
        final GameType gameType = (GameType) getAdditionalDataValue(ADDITIONAL_DATA_GAME_TYPE_KEY);
        final LevelDifficulty levelDifficulty = (LevelDifficulty) getAdditionalDataValue(ADDITIONAL_DATA_LEVEL_DIFFICULTY_KEY);
        return equationGeneratorService.generateEquations(gameType, levelDifficulty);
    }

    private Queue<MathEquationActor> convertMathEquationsToActors(List<MathEquation> mathEquations) {
        final Queue<MathEquationActor> result = new ArrayDeque<>();
        int yCounter = 200;
        for (final MathEquation mathEquation : mathEquations) {
            result.add(new MathEquationActor(600, yCounter, mathEquation, font));
            yCounter += 200;
        }
        return result;
    }

    @Override
    public void disposeStage() {
        buttonOk.remove();
        buttonNo.remove();
    }

    @Override
    public void draw() {
        super.draw();

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (buttonOk.isClicked()) {
            buttonOk.setClicked(false);
            final MathEquationActor currentEquation = mathEquationActors.poll();
            currentEquation.remove();
            for (final MathEquationActor mathEquationActor : mathEquationActors) {
                mathEquationActor.moveDown();
            }

        }
    }

    @Override
    public boolean keyDown(int keyCode) {
        super.keyDown(keyCode);
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            setTargetScreenType(ScreenType.MENU);
            return true;
        }
        return false;
    }

}
