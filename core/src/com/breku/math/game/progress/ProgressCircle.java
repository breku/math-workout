package com.breku.math.game.progress;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by brekol on 15.10.16.
 */
public class ProgressCircle extends Actor {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();


    public ProgressCircle() {

        shapeRenderer.setAutoShapeType(true);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();

        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(100, 400, 50);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.arc(100,400,50,0,30,50);

        shapeRenderer.end();

        batch.begin();

    }
}
