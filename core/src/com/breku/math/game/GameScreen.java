package com.breku.math.game;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.AbstractScreen;

/**
 * Created by brekol on 13.10.16.
 */
public class GameScreen extends AbstractScreen {

    public GameScreen(GoogleApiService googleApiService) {
        super(new GameStage(googleApiService));
    }
}
