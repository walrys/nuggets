package com.bnw.nuggetdance.Sprites;

import com.bnw.nuggetdance.Debug.InterfaceDebug;
import com.bnw.nuggetdance.Music.DanceMusic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brandon on 28/12/16.
 */

public class Demonstrator {
    private static final int TIMER_CONSTANT = 2 * 60;

    private String currentLeftMove;
    private String currentRightMove;
    private String prevLeftMove;
    private String prevRightMove;
    private ArrayList<String> moveStrings;
    private Random randomGenerator;

    private InterfaceDebug debugInterface;

    private int moveAlarm;

    public Demonstrator(InterfaceDebug debugInterface)  {
        this.moveStrings = new ArrayList<String>();
        this.randomGenerator = new Random();
        this.debugInterface = debugInterface;
        this.moveAlarm = TIMER_CONSTANT;

        this.currentLeftMove = "NONE";
        this.currentRightMove = "NONE";

        this.prevLeftMove = "NONE";
        this.prevRightMove = "NONE";

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

    }

    public boolean update(float dt, DanceMusic danceMusic) {

        // update move generator
        if (danceMusic.isCorrectBeat()) {
            generateRandomMove(debugInterface);
            return true;
        }
        return false;
    }

    public boolean generateRandomMove(InterfaceDebug debugInterface) {
        prevLeftMove = currentLeftMove;
        prevRightMove = currentRightMove;

        /*int direction = randomGenerator.nextInt(2);

        if (direction == 0) {
            currentLeftMove = armToGenerate(prevLeftMove);

            if (isMoveEquals(currentLeftMove, "NONE"))  {
                moveStrings.remove(0);
            }

            currentRightMove = armToGenerate(prevRightMove);

            if (isMoveEquals(currentLeftMove, "NONE"))  {
                moveStrings.add(0, "NONE");
            }
        } else if (direction == 1)  {
            currentRightMove = armToGenerate(prevRightMove);

            if (isMoveEquals(currentRightMove, "NONE"))  {
                moveStrings.remove(0);
            }

            currentLeftMove = armToGenerate(prevLeftMove);

            if (isMoveEquals(currentRightMove, "NONE"))  {
                moveStrings.add(0, "NONE");
            }
        }*/

        do {
            int randomLeftIndex = randomGenerator.nextInt(8);
            int randomRightIndex = randomGenerator.nextInt(8);

            currentLeftMove = moveStrings.get(randomLeftIndex);
            currentRightMove = moveStrings.get(randomRightIndex);
        }
        while ((isMoveEquals(this.currentLeftMove, "NONE") && isMoveEquals(this.currentRightMove, "NONE"))
            || (isMoveEquals(this.currentLeftMove, this.prevLeftMove) && isMoveEquals(this.currentRightMove, this.prevRightMove)));

        //update debug label
        debugInterface.getDemoLeftLabel().setText(currentLeftMove);
        debugInterface.getDemoRightLabel().setText(currentRightMove);

        return true;
    }

    public String armToGenerate(String previous) {
        int removeIndex = -1;
        if (moveStrings.contains(previous)) {
            removeIndex = moveStrings.indexOf(previous);
        }
        moveStrings.remove(previous);

        int randomIndex = randomGenerator.nextInt(7);

        if (removeIndex != -1) {
            moveStrings.add(removeIndex, previous);
        }

        return moveStrings.get(randomIndex);
    }


    public boolean isMoveEquals(String firstString, String secondString) {
        return firstString.equalsIgnoreCase(secondString);
    }
}
