package com.breku.math.mainmenu.button;

import com.badlogic.gdx.graphics.Texture;
import com.breku.math.integration.GoogleApiService;

/**
 * Created by brekol on 13.10.16.
 */
public class PlayWithFriendButton extends AbstractMenuButton {

    private final GoogleApiService googleApiService;

    public PlayWithFriendButton(Texture texture,GoogleApiService googleApiService) {
        super(texture, 600, 500);
        this.googleApiService = googleApiService;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isClicked()) {
            setClicked(false);
            googleApiService.launchInvitePlayersScreen();
        }
    }
}
