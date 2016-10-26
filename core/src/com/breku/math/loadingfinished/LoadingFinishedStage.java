package com.breku.math.loadingfinished;

import com.breku.math.configuration.ContextConstants;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brekol on 25.10.16.
 */
public class LoadingFinishedStage extends AbstractStage {


    private Map<String, Object> additionalDataCopy;

    public LoadingFinishedStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        additionalDataCopy = new HashMap<>(getAdditionalData());
    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        bigFont.draw(getBatch(), "Loading has finished, click anywhere to start.", 200, 500);
        getBatch().end();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        setAdditionalData(additionalDataCopy);
        if (additionalDataCopy.get(ContextConstants.ADDITIONAL_DATA_SET_GAME_TYPE) != null &&
                (boolean) additionalDataCopy.get(ContextConstants.ADDITIONAL_DATA_SET_GAME_TYPE)){
            setTargetScreenType(ScreenType.GAME_TYPE);
        }else {
            setTargetScreenType(ScreenType.GAME);
        }
        return true;
    }
}
