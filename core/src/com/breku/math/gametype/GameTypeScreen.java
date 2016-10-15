package com.breku.math.gametype;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.AssetManagerWrapper;

/**
 * Created by brekol on 15.10.16.
 */
public class GameTypeScreen extends AbstractScreen {

    public GameTypeScreen(final GoogleApiService googleApiService, final AssetManagerWrapper assetManagerWrapper) {
        super(new GameTypeStage(googleApiService, assetManagerWrapper));
    }
}
