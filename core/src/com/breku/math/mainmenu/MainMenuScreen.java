package com.breku.math.mainmenu;

import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.TextureManager;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 10.10.16.
 */
public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(GoogleApiService googleApiService, TextureManager textureManager) {
        super(new MainMenuStage(googleApiService,textureManager));
    }
}
