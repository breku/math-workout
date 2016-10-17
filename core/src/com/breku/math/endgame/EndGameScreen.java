package com.breku.math.endgame;

import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.AssetManagerWrapper;

/**
 * Created by brekol on 16.10.16.
 */
public class EndGameScreen extends AbstractScreen {

    public EndGameScreen(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(new EndGameStage(googleApiService, assetManagerWrapper));
    }
}
