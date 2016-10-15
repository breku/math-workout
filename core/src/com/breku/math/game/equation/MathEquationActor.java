package com.breku.math.game.equation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.breku.math.core.AbstractActor;

/**
 * Created by brekol on 15.10.16.
 */
public class MathEquationActor extends AbstractActor {

    private final String mathEquation;
    private final BitmapFont bitmapFont;
    private final boolean correct;

    public MathEquationActor(MathEquation mathEquation, BitmapFont bitmapFont) {
        this.mathEquation = mathEquation.getStringRepresentation();
        correct = mathEquation.isCorrect();
        this.bitmapFont = bitmapFont;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        bitmapFont.draw(batch, mathEquation, getX(), getY());
    }

    public void moveDown() {
        addAction(Actions.moveBy(0, -200, 0.25f));
    }
}
