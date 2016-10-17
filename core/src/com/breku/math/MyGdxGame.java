package com.breku.math;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.ScreenManager;
import com.breku.math.screen.manager.AssetManagerWrapper;

public class MyGdxGame extends Game {
    private static final String TAG = "MyGdxGame";
    private final GoogleApiService googleApiService;
    private SpriteBatch batch;
    private AbstractScreen currentScreen;
    private AssetManagerWrapper assetManagerWrapper;
    private ScreenManager screenManager;

    public MyGdxGame(GoogleApiService googleApiService) {
        this.googleApiService = googleApiService;
    }

    @Override
    public void create() {
        initialize();

    }

    private void initialize() {
        Gdx.app.log(TAG, ">> Initializing app");
        Gdx.input.setCatchBackKey(true);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        batch = new SpriteBatch();
        assetManagerWrapper = new AssetManagerWrapper();
        screenManager = new ScreenManager(googleApiService, assetManagerWrapper);

        Gdx.app.log(TAG, "<< Initializing app finished");
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (assetManagerWrapper.update()) {

            if (currentScreen == null) {
                currentScreen = screenManager.getInitScreen();
                setScreen(currentScreen);
            }

            batch.begin();
            currentScreen.render(Gdx.graphics.getDeltaTime());
            screenManager.handleTargetScreenType(this, currentScreen);
            batch.end();
        }
    }

    public void setScreen(final AbstractScreen screen) {
        super.setScreen(screen);
        currentScreen = screen;
    }
}
