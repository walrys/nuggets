package com.bnw.nuggetdance.Sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bnw.nuggetdance.Constants.AssetConstants;

/**
 * Created by Brandon on 13/1/17.
 */

public class Circle extends Image {

    public Circle(AssetManager manager) {
        super(manager.get(AssetConstants.SPR_CIRCLE, Texture.class));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
