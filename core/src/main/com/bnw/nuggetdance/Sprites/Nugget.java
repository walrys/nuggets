package com.bnw.nuggetdance.Sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bnw.nuggetdance.Music.DanceMusic;

/**
 * Created by Brandon on 30/12/16.
 */

public interface Nugget {

    String setArmTexture(boolean isLeft, int armPosition);
    void update(float dt, DanceMusic danceMusic, OrthographicCamera gameCam);
    void draw(Batch batch, AssetManager assetManager);
    String getArm(int arm);
}
