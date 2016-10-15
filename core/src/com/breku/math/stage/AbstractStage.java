package com.breku.math.stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.breku.math.configuration.ContextConstants;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.Background;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.screen.manager.AssetType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brekol on 10.10.16.
 */
public abstract class AbstractStage extends Stage {

    protected final GoogleApiService googleApiService;
    protected final AssetManagerWrapper assetManagerWrapper;
    protected BitmapFont font;
    private Map<String, Object> additionalData = new HashMap<>();
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

    public Object getAdditionalDataValue(final String key) {
        return additionalData.get(key);
    }

    public void addAdditionalData(final String key, final Object value) {
        this.additionalData.put(key, value);
    }

    public ScreenType getTargetScreenType() {
        return targetScreenType;
    }

    public void setTargetScreenType(ScreenType targetScreenType) {
        this.targetScreenType = targetScreenType;
    }

    public void initialize() {
        font = assetManagerWrapper.getFont(AssetType.COMIC_SANS_FONT);
        font.setColor(Color.BLACK);
        addActor(new Background(assetManagerWrapper.getTexture(AssetType.BLACKBOARD_TEXTURE)));
    }

    public abstract void disposeStage();
}
