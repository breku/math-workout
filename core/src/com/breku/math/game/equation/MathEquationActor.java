package com.breku.math.game.equation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by brekol on 15.10.16.
 */
public class MathEquationActor extends Actor {

    public MathEquationActor(Texture texture, float actorX, float actorY, MathEquation mathEquation) {
        setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
        setDebug(true);
    }
}
