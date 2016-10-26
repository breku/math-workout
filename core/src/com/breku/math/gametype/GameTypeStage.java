package com.breku.math.gametype;

import com.badlogic.gdx.Input;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;
import com.breku.math.integration.GameIntegrationCallbackValue;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.LoadingFinishedCallback;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.ArrayList;
import java.util.List;

import static com.breku.math.configuration.ContextConstants.*;
import static com.breku.math.screen.manager.AssetType.GAME_TYPE_PLAY;

/**
 * Created by brekol on 15.10.16.
 */
public class GameTypeStage extends AbstractStage {

    private List<PlayButton> playButtons;
    private boolean additionalDataQuickMatch;
    private boolean additionalDataSelectPlayers;

    public GameTypeStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        playButtons = new ArrayList<>();

        final Object additionalDataQuickMatch = getAdditionalDataValue(ADDITIONAL_DATA_QUICKMATCH);
        final Object additionalDataSelectPlayers = getAdditionalDataValue(ADDITIONAL_DATA_SELECT_PLAYERS);
        this.additionalDataQuickMatch = additionalDataQuickMatch != null && (boolean) additionalDataQuickMatch;
        this.additionalDataSelectPlayers = additionalDataSelectPlayers != null && (boolean) additionalDataSelectPlayers;


        int x = 200;
        for (final LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            int y = 700;
            for (final GameType gameType : GameType.values()) {
                playButtons.add(new PlayButton(assetManagerWrapper.getTexture(GAME_TYPE_PLAY), x, y, gameType, levelDifficulty));
                y -= 200;
            }
            x += 500;
        }

        for (PlayButton playButton : playButtons) {
            addActor(playButton);
        }
    }

    @Override
    public void disposeStage() {
        for (PlayButton playButton : playButtons) {
            playButton.remove();
        }
    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        for (final PlayButton playButton : playButtons) {
            font.draw(getBatch(), playButton.getGameType().name() + " " + playButton.getLevelDifficulty().name(), playButton.getX() + 100, playButton.getY() + 200);
        }

        getBatch().end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (PlayButton playButton : playButtons) {
            if (playButton.isClicked()) {
                playButton.setClicked(false);
                addAdditionalData(ADDITIONAL_DATA_GAME_TYPE_KEY, playButton.getGameType());
                addAdditionalData(ADDITIONAL_DATA_LEVEL_DIFFICULTY_KEY, playButton.getLevelDifficulty());

                GameIntegrationCallbackValue callbackValue = new GameIntegrationCallbackValue(playButton.getLevelDifficulty(), playButton.getGameType());
                googleApiService.takeTurnAsMyself(new LoadingFinishedCallback(this, callbackValue));
                setTargetScreenType(ScreenType.LOADING);
            }
        }

    }

    @Override
    public boolean keyDown(int keyCode) {
        super.keyDown(keyCode);
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            setTargetScreenType(ScreenType.MENU);
            return true;
        }
        return false;
    }
}
