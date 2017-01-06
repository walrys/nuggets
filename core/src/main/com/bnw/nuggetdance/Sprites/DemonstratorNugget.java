package com.bnw.nuggetdance.Sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Constants.AssetConstants;
import com.bnw.nuggetdance.Debug.InterfaceDebug;
import com.bnw.nuggetdance.Music.DanceMusic;
import com.bnw.nuggetdance.Nuggets;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brandon on 28/12/16.
 */

public class DemonstratorNugget extends Sprite implements Nugget   {
    private String currentLeftMove;
    private String currentRightMove;
    private String prevLeftMove;
    private String prevRightMove;

    private int isCurrentMatched;
    private int isPrevMatched;
    private ArrayList<String> moveStrings;
    private Random randomGenerator;

    private InterfaceDebug debugInterface;
    private boolean bob;
    private long timer;

    private String leftArm, rightArm;

    public DemonstratorNugget(Nuggets game)  {
        super(game.assetManager.get(AssetConstants.SPR_DEMO_BODY, Texture.class));

        this.leftArm = new String(AssetConstants.SPR_BONE_ARM_LEFT_0);
        this.rightArm = new String(AssetConstants.SPR_BONE_ARM_RIGHT_0);

        this.moveStrings = new ArrayList<String>();
        this.randomGenerator = new Random();
        this.debugInterface = game.debugInterface;

        this.currentLeftMove = "NONE";
        this.currentRightMove = "NONE";

        this.prevLeftMove = "NONE";
        this.prevRightMove = "NONE";

        this.isCurrentMatched = 1;
        this.isPrevMatched = -1;

        // add array list of moves
        this.moveStrings.add("NONE");
        this.moveStrings.add("NORTHWEST");
        this.moveStrings.add("NORTH");
        this.moveStrings.add("NORTHEAST");
        this.moveStrings.add("WEST");
        this.moveStrings.add("EAST");
        this.moveStrings.add("SOUTHWEST");
        this.moveStrings.add("SOUTH");
        this.moveStrings.add("SOUTHEAST");


        setScale(0.5f);

        this.bob = false;
        this.timer = 0;
    }

    public void update(float dt, DanceMusic danceMusic, OrthographicCamera gameCam) {
        // update move generator
        if (danceMusic.isCorrectBeat()) {
            generateRandomMove(debugInterface);
            isCurrentMatched = isPrevMatched;
        }

        if (danceMusic.getBeat() != timer)   {
            bob = !bob;
            timer = danceMusic.getBeat();
        }

        // move nugget
        float x_offset = ApplicationConstants.WIDTH*0.5f - getWidth();
        if(bob)
            setPosition(x_offset - gameCam.position.x,ApplicationConstants.HEIGHT*0.625f - getHeight());
        else
            setPosition(x_offset - gameCam.position.x,ApplicationConstants.HEIGHT*0.625f - getHeight() - ApplicationConstants.BOUNCE_HEIGHT);
    }

    public boolean generateRandomMove(InterfaceDebug debugInterface) {
        prevLeftMove = currentLeftMove;
        prevRightMove = currentRightMove;
        int randomLeftIndex = 0;
        int randomRightIndex = 0;

        do {
            randomLeftIndex = randomGenerator.nextInt(8);
            randomRightIndex = randomGenerator.nextInt(8);

            currentLeftMove = moveStrings.get(randomLeftIndex);
            currentRightMove = moveStrings.get(randomRightIndex);
        }
        while ((isMoveEquals(this.currentLeftMove, "NONE") && isMoveEquals(this.currentRightMove, "NONE"))
            || (isMoveEquals(this.currentLeftMove, this.prevLeftMove) && isMoveEquals(this.currentRightMove, this.prevRightMove)));

        //update debug label
        debugInterface.getDemoLeftLabel().setText(currentLeftMove);
        debugInterface.getDemoRightLabel().setText(currentRightMove);

        // update arms position
        leftArm = setArmTexture(true, randomLeftIndex);
        rightArm = setArmTexture(false, randomRightIndex);

        return false;
    }

    public boolean isMoveEquals(String firstString, String secondString) {
        return firstString.equalsIgnoreCase(secondString);
    }

    @Override
    public String setArmTexture(boolean isLeft, int armPosition) {
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

    @Override
    public void draw(Batch batch, AssetManager assetManager) {
        batch.begin();
        super.draw(batch);
        batch.draw(assetManager.get(leftArm, Texture.class), getX()*0.75f, getY() + ApplicationConstants.HEIGHT*0.1f);
        batch.draw(assetManager.get(rightArm, Texture.class), getX()*0.75f, getY() + ApplicationConstants.HEIGHT*0.1f);
        batch.end();
    }

    @Override
    public String getArm(int arm) {
        String armName = new String();
        if (arm == 0)   {
            armName = leftArm;
        } else if (arm == 1)    {
            armName = rightArm;
        }
        return armName;
    }

    public int getMatchCurrent()    {
        return isCurrentMatched;
    }

    public int getMatchPrev()   {
        return isPrevMatched;
    }

    public void setMatchCurrent(int isMatched) {
        this.isCurrentMatched = isMatched;
    }

    public void setMatchPrev(int isMatched) {
        this.isPrevMatched = isMatched;
    }
}
