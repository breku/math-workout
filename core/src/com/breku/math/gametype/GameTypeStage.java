package com.breku.math.gametype;

import com.badlogic.gdx.Input;
import com.breku.math.game.level.GameType;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.ArrayList;
import java.util.List;

import static com.breku.math.configuration.ContextConstants.ADDITIONAL_DATA_GAME_TYPE_KEY;
import static com.breku.math.configuration.ContextConstants.ADDITIONAL_DATA_LEVEL_DIFFICULTY_KEY;
import static com.breku.math.game.level.LevelDifficulty.*;
import static com.breku.math.screen.manager.AssetType.GAME_TYPE_PLAY;

/**
 * Created by brekol on 15.10.16.
 */
public class GameTypeStage extends AbstractStage {

    private List<PlayButton> playButtons;

    public GameTypeStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        playButtons = new ArrayList<>();
        playButtons.add(new PlayButton(assetManagerWrapper.getTexture(GAME_TYPE_PLAY), 200, 600, GameType.ADD, EASY));
        playButtons.add(new PlayButton(assetManagerWrapper.getTexture(GAME_TYPE_PLAY), 200, 400, GameType.ADD, MEDIUM));
        playButtons.add(new PlayButton(assetManagerWrapper.getTexture(GAME_TYPE_PLAY), 200, 200, GameType.ADD, HARD));

        playButtons.add(new PlayButton(assetManagerWrapper.getTexture(GAME_TYPE_PLAY), 800, 600, GameType.SUB, EASY));
        playButtons.add(new PlayButton(assetManagerWrapper.getTexture(GAME_TYPE_PLAY), 800, 400, GameType.SUB, MEDIUM));
        playButtons.add(new PlayButton(assetManagerWrapper.getTexture(GAME_TYPE_PLAY), 800, 200, GameType.SUB, HARD));


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
                setTargetScreenType(ScreenType.GAME);
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
