package com.bnw.nuggetdance.Backgrounds;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Constants.AssetConstants;

/**
 * Created by Brandon on 23/12/16.
 */
public class PlayBackground extends Background {
    private static float x = 0;
    private static float y = 0;

    private Texture playsketch_1;
    private Texture playsketch_2;

    public PlayBackground(AssetManager assetManager) {
        playsketch_1 = assetManager.get(AssetConstants.BACKGROUND_PLAY_1, Texture.class);
        playsketch_2 = assetManager.get(AssetConstants.BACKGROUND_PLAY_2, Texture.class);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.begin();
        sb.draw(playsketch_1, x, y);
        sb.draw(playsketch_2, x + ApplicationConstants.WIDTH, y);
        sb.end();
    }

    @Override
    public boolean dispose() {
        playsketch_1.dispose();
        playsketch_2.dispose();

        return true;
    }

    @Override
    public float getXCoordinate() {
        return x;
    }

    @Override
    public float getYCoordinate() {
        return y;
    }

    @Override
    public float setXCoordinate(float x)    {
        this.x = x;
        return this.x;
    }

    @Override
    public float setYCoordinate(float y)    {
        this.y = y;
        return this.y;
    }

    @Override
    public float moveXCoordinate(float offset)   {
        this.x += offset;
        return this.x;
    }

    @Override
    public float moveYCoordinate(float offset)   {
        this.y += offset;
        return this.y;
    }
}
