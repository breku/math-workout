package com.breku.math.game;

import com.breku.math.game.button.AbstractGameButton;
import com.breku.math.game.button.ButtonNo;
import com.breku.math.game.button.ButtonOk;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.manager.TextureManager;
import com.breku.math.stage.AbstractStage;

import static com.breku.math.screen.manager.TextureType.NO_BUTTON_TEXTURE;
import static com.breku.math.screen.manager.TextureType.OK_BUTTON_TEXTURE;

/**
 * Created by brekol on 13.10.16.
 */
public class GameStage extends AbstractStage {

    private AbstractGameButton buttonOk, buttonNo;

    public GameStage(GoogleApiService googleApiService, TextureManager textureManager) {
        super(googleApiService, textureManager);
    }

    @Override
    public void initialize() {
        super.initialize();
        buttonNo = new ButtonNo(textureManager.getTexture(NO_BUTTON_TEXTURE));
        buttonOk = new ButtonOk(textureManager.getTexture(OK_BUTTON_TEXTURE));

        addActor(buttonNo);
        addActor(buttonOk);
    }

    @Override
    public void disposeStage() {
        buttonOk.remove();
        buttonNo.remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);


    }
}
