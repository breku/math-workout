package com.breku.math.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.breku.math.configuration.ContextConstants;

/**
 * Created by brekol on 10.10.16.
 */
public class AbstractStage extends Stage {

    public AbstractStage() {
        super(new StretchViewport(ContextConstants.SCREEN_WIDTH, ContextConstants.SCREEN_HEIGHT));
    }

    public void initialize() {
        // intentionally left blank
    }
}
