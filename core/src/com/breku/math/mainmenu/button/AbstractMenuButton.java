package com.breku.math.mainmenu.button;

import com.badlogic.gdx.graphics.Texture;
import com.breku.math.button.AbstractButton;

/**
 * Created by brekol on 06.12.15.
 */
public abstract class AbstractMenuButton extends AbstractButton {

    public AbstractMenuButton(Texture texture, float actorX, float actorY) {
        super(texture, actorX, actorY, 0.6f, 0.8f);
    }
}
