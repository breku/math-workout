package com.breku.math.game;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.TextureManager;

/**
 * Created by brekol on 13.10.16.
 */
public class GameScreen extends AbstractScreen {

    public GameScreen(GoogleApiService googleApiService, TextureManager textureManager) {
        super(new GameStage(googleApiService,textureManager));
    }
}
