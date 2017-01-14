package com.bnw.nuggetdance.Misc.SFX;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bnw.nuggetdance.Constants.ApplicationConstants;

/**
 * Created by Brandon on 11/1/17.
 */

public class SFXHandler {
    private ShapeRenderer shapeRenderer;

    public SFXHandler() {
        this.shapeRenderer = new ShapeRenderer();
    }

    public float fadeScreen(float fadeSpeed, float fadeAlpha)  {
        fadeAlpha += fadeSpeed;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, fadeAlpha);
        shapeRenderer.rect(0, 0, ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT);
        shapeRenderer.end();

        return fadeAlpha;
    }

    // fade speed is -ve if fade out
    // fade speed is +ve if fade in
    public float fadeText(float fadeSpeed, float fadeAlpha, Label label)   {
        fadeAlpha += fadeSpeed;

        label.setColor(label.getColor().r, label.getColor().g, label.getColor().b, fadeAlpha);

        return fadeAlpha;
    }

    public void reduceSize(Label label, float scale)    {
        float xScale = label.getFontScaleX();
        if (xScale >= 1) {
            label.setFontScale(xScale - scale);
        }
    }
}
