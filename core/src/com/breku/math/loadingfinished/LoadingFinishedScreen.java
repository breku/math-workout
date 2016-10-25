package com.breku.math.loadingfinished;

import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.AssetManagerWrapper;

/**
 * Created by brekol on 25.10.16.
 */
public class LoadingFinishedScreen extends AbstractScreen {

    public LoadingFinishedScreen(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(new LoadingFinishedStage(googleApiService, assetManagerWrapper));
    }
}
