package com.bnw.nuggetdance.Backgrounds;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bnw.nuggetdance.Constants.AssetConstants;

/**
 * Created by Brandon on 23/12/16.
 */
public class MainBackground extends Background  {
    private static float x = 0;
    private static float y = 0;

    private Texture mainsketch;

    public MainBackground(AssetManager assetManager) {
        mainsketch = assetManager.get(AssetConstants.BACKGROUND_MAIN_1, Texture.class);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.begin();
        sb.draw(mainsketch, x, y);
        sb.end();
    }

    @Override
    public boolean dispose()    {
        mainsketch.dispose();
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
        return x;
    }

    @Override
    public float setYCoordinate(float y)    {
        this.y = y;
        return y;
    }

    @Override
    public float moveXCoordinate(float offset) {
        this.x += offset;
        return this.x;
    }

    @Override
    public float moveYCoordinate(float offset) {
        this.y += offset;
        return this.y;
    }
}
