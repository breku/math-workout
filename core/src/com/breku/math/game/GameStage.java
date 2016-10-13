package com.breku.math.game;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.manager.TextureManager;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 13.10.16.
 */
public class GameStage extends AbstractStage {

    public GameStage(GoogleApiService googleApiService, TextureManager textureManager) {
        super(googleApiService, textureManager);
    }

    @Override
    public void disposeStage() {

    }
}
