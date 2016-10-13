package com.breku.math.mainmenu.button;

import com.badlogic.gdx.graphics.Texture;
import com.breku.math.googleplay.GoogleApiService;

/**
 * Created by brekol on 13.10.16.
 */
public class PlayWithFriendButton extends AbstractMenuButton {

    private final GoogleApiService googleApiService;

    public PlayWithFriendButton(GoogleApiService googleApiService) {
        super(new Texture("gfx/menu/play_with_friend.png"), 600, 500);
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
