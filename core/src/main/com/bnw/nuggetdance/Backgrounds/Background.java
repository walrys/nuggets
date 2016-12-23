package com.bnw.nuggetdance.Backgrounds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Brandon on 23/12/16.
 */
public abstract class Background {

    public Background()  {
    }

    public abstract void draw(SpriteBatch sb);
    public abstract boolean dispose();
    public abstract float getXCoordinate();
    public abstract float getYCoordinate();
    public abstract float setXCoordinate(float x);
    public abstract float setYCoordinate(float y);
    public abstract float moveXCoordinate(float offset);
    public abstract float moveYCoordinate(float offset);

}
