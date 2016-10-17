package com.breku.math.mainmenu;

import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.AssetManagerWrapper;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(new MainMenuStage(googleApiService, assetManagerWrapper));
    }
}
