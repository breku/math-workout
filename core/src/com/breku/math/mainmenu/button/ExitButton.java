package com.breku.math.mainmenu.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by brekol on 13.10.16.
 */
public class ExitButton extends AbstractMenuButton {

    private static final String TAG = "ExitButton";

    public ExitButton(Texture texture) {
        super(texture, 1200, 0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isClicked()) {
            setClicked(false);
            Gdx.app.exit();
        }
    }
}
