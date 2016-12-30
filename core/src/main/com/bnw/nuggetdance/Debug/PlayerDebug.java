package com.bnw.nuggetdance.Debug;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Constants.AssetConstants;
import com.bnw.nuggetdance.Nuggets;


/**
 * Created by Walrus on 12/29/2016.
 */

public class PlayerDebug extends Sprite {
    private boolean bob = false;
    private AssetManager assetManager;
    private int timer = 0;
    private Nuggets game;
    private Texture leftArm, rightArm;
    
    public PlayerDebug(Nuggets game, AssetManager assetManager){
        super(assetManager.get(AssetConstants.SPR_PLAYER_BONE, Texture.class));
        this.assetManager = assetManager;
        setArmTexture(true, 0);
        setArmTexture(false, 0);
        this.game = game;
        setPosition(ApplicationConstants.WIDTH/2 - getWidth()/2,ApplicationConstants.HEIGHT/2 - getHeight()/2);
    }
    
    public void setArmTexture(boolean isLeft, int armPosition){
        if(isLeft) {
            switch (armPosition) {
                case 0:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_0, Texture.class);
                    break;
                case 1:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_1, Texture.class);
                    break;
                case 2:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_2, Texture.class);
                    break;
                case 3:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_3, Texture.class);
                    break;
                case 4:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_4, Texture.class);
                    break;
                case 5:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_5, Texture.class);
                    break;
                case 6:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_6, Texture.class);
                    break;
                case 7:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_7, Texture.class);
                    break;
                case 8:
                    leftArm = assetManager.get(AssetConstants.SPR_BONE_ARM_LEFT_8, Texture.class);
                    break;
            }
        }
        else {
            switch (armPosition) {
                case 0:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_0, Texture.class);
                    break;
                case 1:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_1, Texture.class);
                    break;
                case 2:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_2, Texture.class);
                    break;
                case 3:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_3, Texture.class);
                    break;
                case 4:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_4, Texture.class);
                    break;
                case 5:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_5, Texture.class);
                    break;
                case 6:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_6, Texture.class);
                    break;
                case 7:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_7, Texture.class);
                    break;
                case 8:
                    rightArm = assetManager.get(AssetConstants.SPR_BONE_ARM_RIGHT_8, Texture.class);
                    break;
            }
        }
    }

    public void update(float dt){
        timer++;
        if(timer%ApplicationConstants.BOUNCE_SPEED == 0) {
            bob = !bob;
            timer = 0;
        }

        float x_offset = ApplicationConstants.WIDTH/2 - getWidth()/2;
        if(bob)
            setPosition(x_offset - game.gameCam.position.x,ApplicationConstants.HEIGHT/2 - getHeight()/2);
        else
            setPosition(x_offset - game.gameCam.position.x,ApplicationConstants.HEIGHT/2 - getHeight()/2 - ApplicationConstants.BOUNCE_HEIGHT);

        setArmTexture(true,InterfaceDebug.currentLeftArmPosition);
        setArmTexture(false,InterfaceDebug.currentRightArmPosition);
    }

    public void draw(Batch batch){
        batch.begin();
        super.draw(batch);
        batch.draw(leftArm,getX(),getY());
        batch.draw(rightArm,getX(),getY());
        batch.end();
    }
}
