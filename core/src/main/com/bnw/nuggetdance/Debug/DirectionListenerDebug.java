package com.bnw.nuggetdance.Debug;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.bnw.nuggetdance.Debug.Interface.InterfacePlayDebug;

/**
 * Created by Brandon on 28/12/16.
 */

public class DirectionListenerDebug extends InputListener{
    TextButton textButton;
    int index;
    int direction;

    public DirectionListenerDebug(TextButton textButton, int index, int direction) {
        this.textButton = textButton;
        this.index = index;
        this.direction = direction;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println(textButton.getLabel().getText() + " Pressed");
        if (direction == 0) {
            InterfacePlayDebug.currentLeftButtonPosition.setText(InterfacePlayDebug.labelStrings.get(index));
            InterfacePlayDebug.setPlayerCurrentArmPosition(index, 0);
            System.out.println(index);
        } else if (direction == 1)  {
            InterfacePlayDebug.currentRightButtonPosition.setText(InterfacePlayDebug.labelStrings.get(index));
            InterfacePlayDebug.setPlayerCurrentArmPosition(index, 1);
            System.out.println(index);
        }
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        System.out.println(textButton.getLabel().getText() + " Entered");
        touchDown(event, x, y, pointer, 0);
        super.enter(event, x, y, pointer, fromActor);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        System.out.println(textButton.getLabel().getText() + " Exited");
        if (direction == 0) {
            InterfacePlayDebug.currentLeftButtonPosition.setText(InterfacePlayDebug.labelStrings.get(0));
            InterfacePlayDebug.setPlayerCurrentArmPosition(0, 0);
        } else if (direction == 1)  {
            InterfacePlayDebug.currentRightButtonPosition.setText(InterfacePlayDebug.labelStrings.get(0));
            InterfacePlayDebug.setPlayerCurrentArmPosition(0, 1);
        }
        super.exit(event, x, y, pointer, toActor);
    }
}
