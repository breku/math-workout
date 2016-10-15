package com.breku.math.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.breku.math.configuration.ContextConstants;

/**
 * Created by brekol on 15.10.16.
 */
public class Background extends Actor {

    private final TextureRegion textureRegion;

    public Background(Texture texture) {
        textureRegion = new TextureRegion(texture);
        setBounds(0, 0, ContextConstants.SCREEN_WIDTH, ContextConstants.SCREEN_HEIGHT);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX
                        (), getScaleY(),
                getRotation());
    }
}
