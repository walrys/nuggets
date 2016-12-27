package com.bnw.nuggetdance.Backgrounds;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Constants.AssetConstants;

/**
 * Created by Brandon on 23/12/16.
 */
public class PlayBackground extends Background {
    private static float x;
    private static float y;

    private Texture playsketch_1;
    private Texture playsketch_2;

    private OrthographicCamera gameCam;

    public PlayBackground(AssetManager assetManager, OrthographicCamera gameCam) {
        this.gameCam = gameCam;
        this.playsketch_1 = assetManager.get(AssetConstants.BACKGROUND_PLAY_1, Texture.class);
        this.playsketch_2 = assetManager.get(AssetConstants.BACKGROUND_PLAY_2, Texture.class);
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.begin();
        sb.draw(playsketch_1, x - playsketch_1.getWidth()/2, y - playsketch_1.getHeight()/2);
        sb.draw(playsketch_2, x - playsketch_2.getWidth()/2 + ApplicationConstants.WIDTH, y - playsketch_2.getHeight()/2);
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
    public float moveXCoordinate(float offset)   {
        this.gameCam.position.x += offset;
        return this.x;
    }

    @Override
    public float moveYCoordinate(float offset)   {
        this.gameCam.position.y += offset;
        return this.y;
    }
}
