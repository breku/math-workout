package com.breku.math.mainmenu;

import com.badlogic.gdx.graphics.Texture;
import com.breku.math.button.AbstractButton;

/**
 * Created by brekol on 10.10.16.
 */
public class PlayButton extends AbstractMenuButton {

    public PlayButton() {
        super(new Texture("gfx/menu/play.png"), 14, 1184);
        setDebug(true);
    }
}
