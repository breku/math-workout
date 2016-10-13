package com.breku.math.mainmenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breku.math.googleplay.GoogleApiService;
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
