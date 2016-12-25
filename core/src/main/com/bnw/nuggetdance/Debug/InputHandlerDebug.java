package com.bnw.nuggetdance.Debug;

import com.badlogic.gdx.Gdx;
import com.bnw.nuggetdance.Backgrounds.PlayBackground;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Screens.MainScreen;
import com.bnw.nuggetdance.Screens.PlayScreen;

/**
 * Created by Brandon on 23/12/16.
 */
public class InputHandlerDebug {

    public InputHandlerDebug() {
    }

    public float handleTouchInput(float dt, PlayScreen screen)   {
        PlayBackground background = screen.getPlayBackground();
        float xShift = 0;

        if(Gdx.input.isTouched())   {
            if(background.getXCoordinate() > -ApplicationConstants.WIDTH) {
                xShift = background.moveXCoordinate(-10);
            }
        } else if (background.getXCoordinate() < 0) {
            xShift = background.moveXCoordinate(10);
        }

        return xShift;
    }

    public float handleTouchInput(float dt, MainScreen screen)    {
        if(Gdx.input.isTouched()){
            screen.getGame().setScreen(new PlayScreen(screen.getGame(), screen.getAssetManager()));
            screen.dispose();
        }

        return -1;
    }
}
