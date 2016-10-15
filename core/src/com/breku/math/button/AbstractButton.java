package com.breku.math.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.breku.math.core.AbstractActor;

/**
 * Created by brekol on 06.12.15.
 */
public class AbstractButton extends AbstractActor {

    private static final String TAG = "AbstractButton";
    private final float originalScale;
    private final float targetScale;
    private final TextureRegion textureRegion;
    private boolean clicked;

    public AbstractButton(Texture texture, float actorX, float actorY, final float originalScale, final float
            targetScale) {
        this.originalScale = originalScale;
        this.targetScale = targetScale;
        textureRegion = new TextureRegion(texture);
        setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
        setScale(originalScale);
        setOrigin(getWidth() / 2, getHeight() / 2);
        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addAction(Actions.scaleTo(AbstractButton.this.targetScale, AbstractButton.this.targetScale));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                addAction(Actions.scaleTo(AbstractButton.this.originalScale, AbstractButton.this.originalScale));
                ((AbstractButton) event.getTarget()).clicked = true;
                Gdx.app.log(TAG, AbstractButton.this.getClass().getSimpleName() + " clicked");
            }
        });
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX
                        (), getScaleY(),
                getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
