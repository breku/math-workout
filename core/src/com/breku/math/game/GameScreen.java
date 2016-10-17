package com.breku.math.game;

import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.AssetManagerWrapper;

/**
 * Created by brekol on 13.10.16.
 */
public class GameScreen extends AbstractScreen {

    public GameScreen(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(new GameStage(googleApiService, assetManagerWrapper));
    }
}
