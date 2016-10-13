package com.breku.math.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breku.math.game.button.AbstractGameButton;
import com.breku.math.game.button.ButtonNo;
import com.breku.math.game.button.ButtonOk;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.screen.manager.AssetType;
import com.breku.math.stage.AbstractStage;

import static com.breku.math.screen.manager.AssetType.NO_BUTTON_TEXTURE;
import static com.breku.math.screen.manager.AssetType.OK_BUTTON_TEXTURE;

/**
 * Created by brekol on 13.10.16.
 */
public class GameStage extends AbstractStage {

    private AbstractGameButton buttonOk, buttonNo;
    private BitmapFont font;

    public GameStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        buttonNo = new ButtonNo(assetManagerWrapper.getTexture(NO_BUTTON_TEXTURE));
        buttonOk = new ButtonOk(assetManagerWrapper.getTexture(OK_BUTTON_TEXTURE));

        font = assetManagerWrapper.getFont(AssetType.COMIC_SANS_FONT);
        font.setColor(Color.BLACK);

        addActor(buttonNo);
        addActor(buttonOk);
    }

    @Override
    public void disposeStage() {
        buttonOk.remove();
        buttonNo.remove();
    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        font.draw(getBatch(), "aaaaaaaaaa0123456789", 500, 500);
        getBatch().end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
