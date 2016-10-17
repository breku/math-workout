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
import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static com.breku.math.configuration.ContextConstants.*;
import static com.breku.math.screen.manager.AssetType.NO_BUTTON_TEXTURE;
import static com.breku.math.screen.manager.AssetType.OK_BUTTON_TEXTURE;

/**
 * Created by brekol on 13.10.16.
 */
public class GameStage extends AbstractStage {

    private static final int NUMBER_OF_VISIBLE_EQUATIONS = 5;
    private AbstractGameButton buttonOk, buttonNo;
    private EquationGeneratorService equationGeneratorService;
    private Queue<MathEquationActor> mathEquationActorQueue;
    private Queue<MathEquationActor> visibleMathEquations;
    private ProgressCircle progressCircle;
    private int score;

    public GameStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        buttonNo = new ButtonNo(assetManagerWrapper.getTexture(NO_BUTTON_TEXTURE));
        buttonOk = new ButtonOk(assetManagerWrapper.getTexture(OK_BUTTON_TEXTURE));
        equationGeneratorService = new EquationGeneratorService();
        progressCircle = new ProgressCircle(getCamera().combined);
        final List<MathEquation> mathEquations = generateMathEquations();
        mathEquationActorQueue = convertMathEquationsToActors(mathEquations);
        visibleMathEquations = getInitialMathEquations(mathEquationActorQueue);
        score = 0;


        for (final MathEquationActor mathEquationActor : visibleMathEquations) {
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
        for (final MathEquation mathEquation : mathEquations) {
            result.add(new MathEquationActor(mathEquation, font));
        }
        return result;
    }

    private Queue<MathEquationActor> getInitialMathEquations(Queue<MathEquationActor> mathEquationActorQueue) {
        final Queue<MathEquationActor> result = new ArrayDeque<>();
        int counterY = 200;
        for (int i = 0; i < NUMBER_OF_VISIBLE_EQUATIONS; i++) {
            final MathEquationActor remove = mathEquationActorQueue.remove();
            remove.setPosition(600, counterY);
            result.add(remove);
            counterY += 200;
        }
        return result;
    }

    @Override
    public void disposeStage() {
        buttonOk.remove();
        buttonNo.remove();
        progressCircle.remove();
    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        font.draw(getBatch(), "Score: " + score, 100, 600);
        getBatch().end();

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (buttonOk.isClicked()) {
            handleAfterUserClick(buttonOk, 1, 2);
        } else if (buttonNo.isClicked()) {
            handleAfterUserClick(buttonNo, -2, -1);
        }

        if (progressCircle.isGameOver()) {
            addAdditionalData(ADDITIONAL_DATA_GAME_SCORE, score);
            setTargetScreenType(ScreenType.END_GAME);
        }
    }

    private void handleAfterUserClick(AbstractGameButton gameButton, int scoreToAddIfCorrectEquation, int scoreToMinusIfWrongEquation) {
        gameButton.setClicked(false);
        final MathEquationActor currentEquation = visibleMathEquations.poll();
        updateScore(currentEquation, scoreToAddIfCorrectEquation, scoreToMinusIfWrongEquation);
        currentEquation.remove();
        final MathEquationActor newEquationFromQueue = getNewEquation();
        addActor(newEquationFromQueue);
        visibleMathEquations.add(newEquationFromQueue);
        moveAllEquationsDown();
    }

    private void updateScore(MathEquationActor currentEquation, int scoreToAddIfCorrectEquation, int scoreToMinusIfWrongEquation) {
        if (currentEquation.isCorrect()) {
            score += scoreToAddIfCorrectEquation;
        } else {
            score -= scoreToMinusIfWrongEquation;
        }
    }

    private MathEquationActor getNewEquation() {
        final MathEquationActor newEquationFromQueue = mathEquationActorQueue.remove();
        newEquationFromQueue.setPosition(visibleMathEquations.peek().getX(), 1200);
        return newEquationFromQueue;
    }

    private void moveAllEquationsDown() {
        for (final MathEquationActor mathEquationActor : visibleMathEquations) {
            mathEquationActor.moveDown();
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
