package com.breku.math.stage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.breku.math.configuration.ContextConstants;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brekol on 10.10.16.
 */
public abstract class AbstractStage extends Stage {

    protected final GoogleApiService googleApiService;
    protected final AssetManagerWrapper assetManagerWrapper;
    protected Map<String, Object> additionalData = new HashMap<>();
    private ScreenType targetScreenType = ScreenType.NONE;


    public AbstractStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(new StretchViewport(ContextConstants.SCREEN_WIDTH, ContextConstants.SCREEN_HEIGHT));
        this.googleApiService = googleApiService;
        this.assetManagerWrapper = assetManagerWrapper;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }

    public ScreenType getTargetScreenType() {
        return targetScreenType;
    }

    public void setTargetScreenType(ScreenType targetScreenType) {
        this.targetScreenType = targetScreenType;
    }

    public void initialize() {
        // intentionally left blank
    }

    public abstract void disposeStage();
}
