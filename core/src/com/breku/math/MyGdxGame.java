package com.breku.math;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.manager.ScreenManager;

public class MyGdxGame extends Game {
    private static final String TAG = "MyGdxGame";
    private final GoogleApiService googleApiService;
    private SpriteBatch batch;
    private AbstractScreen currentScreen;
    private ScreenManager screenManager;

    public MyGdxGame(GoogleApiService googleApiService) {
        this.googleApiService = googleApiService;
    }

    @Override
    public void create() {
        initialize();
        currentScreen = screenManager.getInitScreen();
        setScreen(currentScreen);
    }

    private void initialize() {
        Gdx.app.log(TAG, ">> Initializing app");
        batch = new SpriteBatch();
        screenManager = new ScreenManager(googleApiService);
        Gdx.input.setCatchBackKey(true);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
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


        batch.begin();
        currentScreen.render(Gdx.graphics.getDeltaTime());
        screenManager.handleTargetScreenType(this, currentScreen);
        batch.end();


    }
}
