package com.breku.math.game.progress;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.breku.math.core.AbstractActor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by brekol on 15.10.16.
 */
public class ProgressCircle extends AbstractActor {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float degress = 360;

    public ProgressCircle(Matrix4 projectionMatrix) {

        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(projectionMatrix);


        new Timer().schedule(new ArcTask(), 500, 250);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();

        if (degress > 0) {
            shapeRenderer.begin();
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

            if (degress > 180) {
                shapeRenderer.setColor(Color.GREEN);
            } else if (degress > 90) {
                shapeRenderer.setColor(Color.YELLOW);
            } else {
                shapeRenderer.setColor(Color.RED);
            }
            shapeRenderer.arc(100, 800, 50, 90, degress, 50);

            shapeRenderer.end();
        }
        batch.begin();

    }

    public boolean isGameOver() {
        return degress < 0;
    }

    private class ArcTask extends TimerTask {

        @Override
        public void run() {
            degress -= 20;
        }
    }
}
