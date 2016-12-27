package com.bnw.nuggetdance.Debug;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bnw.nuggetdance.Constants.ApplicationConstants;

/**
 * Created by Brandon on 26/12/16.
 */

public class InterfaceDebug {
    private enum Positions {
        NONE, NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;
    };

    private Positions currentRightButtonPosition;
    private Positions currentLeftButtonPosition;
    private Stage stage;
    private Viewport viewPort;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float x, y;


    public InterfaceDebug(SpriteBatch sb) {
        this.viewPort = new FitViewport(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewPort, sb);
        this.x = 0;
        this.y = 0;

        currentLeftButtonPosition = Positions.NONE;
        currentRightButtonPosition = Positions.NONE;
    }

    public void renderRectangleButtons(OrthographicCamera gameCam)   {
        shapeRenderer.setProjectionMatrix(gameCam.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(x - ApplicationConstants.WIDTH/2, y - ApplicationConstants.HEIGHT/2, ApplicationConstants.WIDTH/6, ApplicationConstants.HEIGHT/9);
        //shapeRenderer.circle(x, y, ApplicationConstants.WIDTH*(1/5));
        shapeRenderer.end();
    }

    public void dispose()   {
        shapeRenderer.dispose();
    }

}
