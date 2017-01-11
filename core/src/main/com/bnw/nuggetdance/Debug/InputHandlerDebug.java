package com.bnw.nuggetdance.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.bnw.nuggetdance.Backgrounds.PlayBackground;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Debug.Interface.InterfacePlayDebug;
import com.bnw.nuggetdance.Screens.MainScreen;
import com.bnw.nuggetdance.Screens.PlayScreen.PlayScreenView;

/**
 * Created by Brandon on 23/12/16.
 */
public class InputHandlerDebug {
    private InterfacePlayDebug debugInterface;

    public InputHandlerDebug(InterfacePlayDebug debugInterface) {
        this.debugInterface = debugInterface;
    }

    public void handleTouchInput(float dt, PlayScreenView screen)   {
        PlayBackground background = screen.getPlayBackground();
        float xShift = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))   {
            if(background.getXCoordinate() < ApplicationConstants.WIDTH) {
                xShift = background.moveXCoordinate(10);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))    {
            if (background.getXCoordinate() > 0) {
                xShift = background.moveXCoordinate(-10);
            }
        }
    }

    public void handleTouchInput(float dt, MainScreen screen)    {
        if(Gdx.input.isTouched()){
            screen.getGame().setScreen(new PlayScreenView(screen.getGame()));
            Gdx.input.setInputProcessor(screen.getGame().debugPlayInterface.getStage());
            screen.dispose();
        }

    }
}
