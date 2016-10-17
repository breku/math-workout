package com.breku.math.mainmenu.button;

import com.badlogic.gdx.graphics.Texture;
import com.breku.math.integration.GoogleApiService;

/**
 * Created by brekol on 13.10.16.
 */
public class PlayWithFriendButton extends AbstractMenuButton {


    public PlayWithFriendButton(Texture texture) {
        super(texture, 600, 500);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isClicked()) {
            setClicked(false);
        }
    }
}
