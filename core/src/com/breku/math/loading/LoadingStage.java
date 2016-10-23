package com.breku.math.loading;

import com.badlogic.gdx.Input;
import com.breku.math.configuration.ContextConstants;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by brekol on 17.10.16.
 */
public class LoadingStage extends AbstractStage {

    private static final String[] LOADING_TEXTS = {"Loading", "Loading .", "Loading ..", "Loading ..."};
    private static final int DELAY_IN_MILISEC = 100;
    private static final int INTERVAL_IN_MILISEC = 500;
    private String loadingText;
    private int loadingTextIndex;
    private Timer timer;


    public LoadingStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();

        loadingTextIndex = 0;
        loadingText = LOADING_TEXTS[loadingTextIndex];

        timer = new Timer();
        timer.schedule(new LoadingTextTask(), DELAY_IN_MILISEC, INTERVAL_IN_MILISEC);

    }

    @Override
    public void disposeStage() {
        super.disposeStage();
        timer.cancel();
    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        bigFont.draw(getBatch(), loadingText, 1000, 500);
        getBatch().end();
    }

    @Override
    public boolean keyDown(int keyCode) {
        super.keyDown(keyCode);
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            addAdditionalData(ContextConstants.ADDITIONAL_BACK_KEY_CLICKED, true);
            setTargetScreenType(ScreenType.MENU);
            return true;
        }
        return false;
    }

    private class LoadingTextTask extends TimerTask {

        @Override
        public void run() {
            loadingTextIndex = (loadingTextIndex + 1) % 4;
            loadingText = LOADING_TEXTS[loadingTextIndex];
        }
    }
}
