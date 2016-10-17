package com.breku.math.loading;

import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 17.10.16.
 */
public class LoadingStage extends AbstractStage {

    public LoadingStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void disposeStage() {

    }
}
