package com.breku.math.mainmenu;

import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuStage extends AbstractStage {

    private AbstractMenuButton playButton;

    @Override
    public void initialize() {
        super.initialize();
        playButton = new PlayButton();
        addActor(playButton);
    }
}
