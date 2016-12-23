package com.bnw.nuggetdance.Backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Brandon on 23/12/16.
 */
public class BackgroundPlay {
    private static final String BACKGROUND_NAME = new String("sketch_play.JPG");

    private static float x = 0;
    private static float y = 0;

    Texture playsketch;

    public BackgroundPlay() {
        playsketch = new Texture(BACKGROUND_NAME);
    }

    public void draw(SpriteBatch sb) {
        sb.begin();
        sb.draw(playsketch, x, y);
        sb.end();
    }

    public float getXCoordinate() {
        return x;
    }

    public float getYCoordinate() {
        return y;
    }

    public float setXCoordinate(float x)    {
        this.x = x;
        return x;
    }

    public float setYCoordinate(float y)    {
        this.y = y;
        return y;
    }
}
