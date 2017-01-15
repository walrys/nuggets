package com.bnw.nuggetdance.Debug;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Constants.AssetConstants;
import com.bnw.nuggetdance.Debug.Interface.InterfacePlayDebug;
import com.bnw.nuggetdance.Music.DanceMusic;
import com.bnw.nuggetdance.Nuggets;
import com.bnw.nuggetdance.Sprites.Nugget;


/**
 * Created by Walrus on 12/29/2016.
 */

public class PlayerDebug extends Sprite implements Nugget{
    private boolean bob = false;
    private AssetManager assetManager;
    private long timer = 0;
    private Nuggets game;
    private String leftArm, rightArm;
    
    public PlayerDebug(Nuggets game, AssetManager assetManager){
        super(assetManager.get(AssetConstants.SPR_PLAYER_BONE, Texture.class));
        this.setScale(0.25f);
        this.assetManager = assetManager;
        this.leftArm = new String(AssetConstants.SPR_BONE_ARM_LEFT_0);
        this.rightArm = new String(AssetConstants.SPR_BONE_ARM_RIGHT_0);
        setArmTexture(true, 0);
        setArmTexture(false, 0);
        this.game = game;
        setPosition(ApplicationConstants.WIDTH/2 - getWidth()/2,ApplicationConstants.HEIGHT/2 - getHeight()/2);
    }
    
    public String setArmTexture(boolean isLeft, int armPosition){
        if(isLeft) {
            switch (armPosition) {
                case 0:
                    return AssetConstants.SPR_BONE_ARM_LEFT_0;
                case 1:
                    return AssetConstants.SPR_BONE_ARM_LEFT_1;
                case 2:
                    return AssetConstants.SPR_BONE_ARM_LEFT_2;
                case 3:
                    return AssetConstants.SPR_BONE_ARM_LEFT_3;
                case 4:
                    return AssetConstants.SPR_BONE_ARM_LEFT_4;
                case 5:
                    return AssetConstants.SPR_BONE_ARM_LEFT_5;
                case 6:
                    return AssetConstants.SPR_BONE_ARM_LEFT_6;
                case 7:
                    return AssetConstants.SPR_BONE_ARM_LEFT_7;
                case 8:
                    return AssetConstants.SPR_BONE_ARM_LEFT_8;
            }
        }
        else {
            switch (armPosition) {
                case 0:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_0;
                case 1:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_1;
                case 2:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_2;
                case 3:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_3;
                case 4:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_4;
                case 5:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_5;
                case 6:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_6;
                case 7:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_7;
                case 8:
                    return AssetConstants.SPR_BONE_ARM_RIGHT_8;
            }
        }
        return null;
    }

    public void update(float dt, DanceMusic danceMusic, OrthographicCamera gameCam){
        /*timer++;
        if(timer%ApplicationConstants.BOUNCE_SPEED == 0) {
            bob = !bob;
            timer = 0;
        }*/

        if (danceMusic.getBeat() != timer) {
            timer = danceMusic.getBeat();
            bob = !bob;
        }

        float x_offset = ApplicationConstants.WIDTH*0.375f - getWidth();
        if(bob)
            setPosition(x_offset - gameCam.position.x,ApplicationConstants.HEIGHT*0.25f - getHeight());
        else
            setPosition(x_offset - gameCam.position.x,ApplicationConstants.HEIGHT*0.25f - getHeight() - ApplicationConstants.BOUNCE_HEIGHT);

        leftArm = setArmTexture(true, InterfacePlayDebug.currentLeftArmPosition);
        rightArm = setArmTexture(false, InterfacePlayDebug.currentRightArmPosition);
    }

    public void draw(Batch batch, AssetManager assetManager){
        batch.begin();
        super.draw(batch);
        batch.draw(assetManager.get(leftArm, Texture.class),getX(),getY());
        batch.draw(assetManager.get(rightArm, Texture.class),getX(),getY());
        batch.end();
    }

    public String getArm(int arm)   {
        String armName = new String();
        if (arm == 0)   {
            armName = leftArm;
        } else if (arm == 1)    {
            armName = rightArm;
        }
        return armName;
    }
}
