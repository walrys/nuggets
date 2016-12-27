package com.bnw.nuggetdance.Backgrounds;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bnw.nuggetdance.Constants.AssetConstants;

/**
 * Created by Brandon on 23/12/16.
 */
public class MainBackground extends Background  {
    private static float x;
    private static float y;

    private Texture mainsketch;
    private OrthographicCamera gameCam;

    public MainBackground(AssetManager assetManager, OrthographicCamera gameCam) {
        mainsketch = assetManager.get(AssetConstants.BACKGROUND_MAIN_1, Texture.class);
        this.gameCam = gameCam;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.begin();
        sb.draw(mainsketch, x - mainsketch.getWidth()/2, y - mainsketch.getHeight()/2);
        sb.end();
    }

    @Override
    public boolean dispose()    {
        mainsketch.dispose();
        return true;
    }

    @Override
    public float getXCoordinate() {
        return gameCam.position.x;
    }

    @Override
    public float getYCoordinate() {
        return gameCam.position.y;
    }

    @Override
    public float setXCoordinate(float x)    {
        gameCam.position.x = x;
        return gameCam.position.x;
    }

    @Override
    public float setYCoordinate(float y)    {
        gameCam.position.y = y;
        return gameCam.position.y;
    }

    @Override
    public float moveXCoordinate(float offset) {
        this.gameCam.position.x += offset;
        return this.x;
    }

    @Override
    public float moveYCoordinate(float offset) {
        this.gameCam.position.y += offset;
        return this.y;
    }
}
