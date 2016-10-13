package com.breku.math.game.button;

import com.badlogic.gdx.graphics.Texture;
import com.breku.math.button.AbstractButton;

/**
 * Created by brekol on 13.10.16.
 */
public abstract class AbstractGameButton extends AbstractButton {

    public AbstractGameButton(Texture texture, float actorX, float actorY) {
        super(texture, actorX, actorY, 1.0f, 1.0f);
    }
}
