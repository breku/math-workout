package com.breku.math.screen.manager;

import com.badlogic.gdx.Gdx;
import com.breku.math.MyGdxGame;
import com.breku.math.endgame.EndGameScreen;
import com.breku.math.game.GameScreen;
import com.breku.math.gametype.GameTypeScreen;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.loading.LoadingScreen;
import com.breku.math.mainmenu.MainMenuScreen;
import com.breku.math.screen.AbstractScreen;
import com.breku.math.screen.ScreenType;

import java.util.Map;

/**
 * Created by brekol on 13.10.16.
 */
public class ScreenManager {
    private static final String TAG = "ScreenManager";

    private final AbstractScreen menuScreen, gameScreen, gameTypeScreen, endGameScreen, loadingScreen;

    private AbstractScreen previousScreenBeforeLoading;

    public ScreenManager(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        menuScreen = new MainMenuScreen(googleApiService, assetManagerWrapper);
        gameScreen = new GameScreen(googleApiService, assetManagerWrapper);
        gameTypeScreen = new GameTypeScreen(googleApiService, assetManagerWrapper);
        endGameScreen = new EndGameScreen(googleApiService, assetManagerWrapper);
        loadingScreen = new LoadingScreen(googleApiService, assetManagerWrapper);
    }

    public AbstractScreen getInitScreen() {
        return menuScreen;
    }

    public void handleTargetScreenType(MyGdxGame myGdxGame, AbstractScreen currentScreen) {
        final ScreenType targetScreenType = currentScreen.getTargetScreenType();
        handleSetupPreviousScreenBeforeLoading(currentScreen, targetScreenType);

//
//
//
//        if (previousScreenBeforeLoading != null && !ScreenType.LOADING.equals(targetScreenType)) {
//            final ScreenType targetScreenTypeFromLoading = previousScreenBeforeLoading.getTargetScreenType();
//            if (!ScreenType.NONE.equals(targetScreenTypeFromLoading)) {
//                changeScreen(myGdxGame, currentScreen, targetScreenTypeFromLoading);
//                previousScreenBeforeLoading = null;
//            }
//        }

        if (!ScreenType.NONE.equals(targetScreenType)) {
            changeScreen(myGdxGame, currentScreen, targetScreenType);
        }
    }

    private void handleSetupPreviousScreenBeforeLoading(AbstractScreen currentScreen, ScreenType targetScreenType) {
        if (ScreenType.LOADING.equals(targetScreenType)) {
            previousScreenBeforeLoading = currentScreen;
        }
    }

    private void changeScreen(MyGdxGame myGdxGame, AbstractScreen currentScreen, ScreenType targetScreenType) {
        Gdx.app.log(TAG, ">> Changing screen to=" + targetScreenType.name());
        disposeCurrentScreen(currentScreen);
        final Map<String, Object> previousScreenAdditionalData = currentScreen.getAdditionalData();
        setupCurrentScreen(myGdxGame, targetScreenType, previousScreenAdditionalData);
        Gdx.app.log(TAG, "<< Changing screen finished");
    }

    private void setupCurrentScreen(MyGdxGame myGdxGame, ScreenType targetScreenType, Map<String, Object> previousScreenAdditionalData) {
        final AbstractScreen newScreen = getTargetScreen(targetScreenType);
        newScreen.setAdditionalData(previousScreenAdditionalData);
        myGdxGame.setScreen(newScreen);
    }

    private void disposeCurrentScreen(AbstractScreen currentScreen) {
        Gdx.app.log(TAG, ">> Disposing current screen=" + currentScreen.getClass().getSimpleName());
        currentScreen.dispose();
        Gdx.app.log(TAG, "<< Disposing finished");
    }

    private AbstractScreen getTargetScreen(ScreenType screenType) {
        if (ScreenType.MENU.equals(screenType)) {
            return menuScreen;
        } else if (ScreenType.GAME.equals(screenType)) {
            return gameScreen;
        } else if (ScreenType.GAME_TYPE.equals(screenType)) {
            return gameTypeScreen;
        } else if (ScreenType.END_GAME.equals(screenType)) {
            return endGameScreen;
        } else if (ScreenType.LOADING.equals(screenType)) {
            return loadingScreen;
        }
        throw new IllegalStateException(String.format("There is no screen type=[%s]", screenType));
    }


}
