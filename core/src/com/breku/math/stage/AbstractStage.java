package com.breku.math.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.breku.math.configuration.ContextConstants;
import com.breku.math.googleplay.GoogleApiService;

/**
 * Created by brekol on 10.10.16.
 */
public class AbstractStage extends Stage {

    protected final GoogleApiService googleApiService;

    public AbstractStage(GoogleApiService googleApiService) {
        super(new StretchViewport(ContextConstants.SCREEN_WIDTH, ContextConstants.SCREEN_HEIGHT));
        this.googleApiService = googleApiService;
    }

    public void initialize() {
        // intentionally left blank
    }
}
