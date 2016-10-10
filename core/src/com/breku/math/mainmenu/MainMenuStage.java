package com.breku.math.mainmenu;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuStage extends AbstractStage {

    private AbstractMenuButton playButton;

    public MainMenuStage(GoogleApiService googleApiService) {
        super(googleApiService);
    }

    @Override
    public void initialize() {
        super.initialize();
        playButton = new PlayButton();
        addActor(playButton);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (playButton.isClicked()) {
            playButton.setClicked(false);
            googleApiService.launchInvitePlayersScreen();
        }
    }
}
