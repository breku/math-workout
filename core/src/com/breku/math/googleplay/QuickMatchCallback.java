package com.breku.math.googleplay;

import com.breku.math.screen.ScreenType;
import com.breku.math.stage.AbstractStage;

/**
 * Created by brekol on 13.10.16.
 */
public class QuickMatchCallback implements LaunchCallback {

    private final AbstractStage stage;

    public QuickMatchCallback(AbstractStage stage) {
        this.stage = stage;
    }

    @Override
    public void onSucces() {
        stage.setTargetScreenType(ScreenType.GAME);
    }

    @Override
    public void onFailure() {

    }
}
