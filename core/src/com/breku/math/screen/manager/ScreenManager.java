package com.breku.math.screen.manager;

import com.badlogic.gdx.Gdx;
import com.breku.math.MyGdxGame;
import com.breku.math.game.GameScreen;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.mainmenu.MainMenuScreen;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.ScreenType;

import java.util.Map;

/**
 * Created by brekol on 13.10.16.
 */
public class ScreenManager {
    private static final String TAG = "ScreenManager";

    private AbstractScreen menuScreen, gameScreen;

    public ScreenManager(GoogleApiService googleApiService) {
        menuScreen = new MainMenuScreen(googleApiService);
        gameScreen = new GameScreen(googleApiService);
    }

    public AbstractScreen getInitScreen() {
        return menuScreen;
    }

    public void handleTargetScreenType(MyGdxGame myGdxGame, AbstractScreen currentScreen) {
        if (!ScreenType.NONE.equals(currentScreen.getTargetScreenType())) {
            final ScreenType targetScreenType = currentScreen.getTargetScreenType();
            Gdx.app.log(TAG, ">> Changing screen to=" + targetScreenType.name());
            disposeCurrentScreen(currentScreen);
            final Map<String, Object> previousScreenAdditionalData = currentScreen.getAdditionalData();
            changeScreen(myGdxGame, targetScreenType, previousScreenAdditionalData);
        }
    }

    private void disposeCurrentScreen(AbstractScreen currentScreen) {
        Gdx.app.log(TAG, ">> Disposing current screen=" + currentScreen.getClass().getSimpleName());
        currentScreen.dispose();
        Gdx.app.log(TAG, "<< Disposing finished");
    }

    private void changeScreen(MyGdxGame myGdxGame, ScreenType targetScreenType, Map<String, Object> previousScreenAdditionalData) {
        final AbstractScreen newScreen = getTargetScreen(targetScreenType);
        newScreen.setAdditionalData(previousScreenAdditionalData);
        myGdxGame.setScreen(newScreen);
    }

    private AbstractScreen getTargetScreen(ScreenType screenType) {
        if (ScreenType.MENU.equals(screenType)) {
            return menuScreen;
        } else if (ScreenType.GAME.equals(screenType)) {
            return gameScreen;
        }
        throw new IllegalStateException(String.format("There is no screen type=[%s]", screenType));
    }


}
