package com.breku.math.loading;

import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.AssetManagerWrapper;

/**
 * Created by brekol on 17.10.16.
 */
public class LoadingScreen extends AbstractScreen {

    public LoadingScreen(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(new LoadingStage(googleApiService, assetManagerWrapper));
    }
}
