package com.bnw.nuggetdance.Screens.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Debug.PlayerDebug;
import com.bnw.nuggetdance.Music.DanceMusic;
import com.bnw.nuggetdance.Nuggets;
import com.bnw.nuggetdance.Sprites.DemonstratorNugget;

/**
 * Created by Brandon on 6/1/17.
 */

public class PlayScreenModel {

    private String SCORE_ALARM = "Score";
    private String ACCURACY_ALARM = "Accuracy";
    private String TAP_ALARM = "Tap";
    private int ALARM_RATE = 60;
    private int SHIFT_RATE = 15;

    private Nuggets game;
    private PlayScreenView view;
    private PlayerDebug player;
    private DemonstratorNugget demo;
    private DanceMusic danceMusic;

    private float accuracy;
    private int combo;
    private boolean showScore;

    public PlayScreenModel(Nuggets game, PlayScreenView view)    {
        this.game = game;
        this.view = view;

        this.demo = new DemonstratorNugget(this.game);
        this.player = new PlayerDebug(game, game.assetManager);
        this.danceMusic = new DanceMusic(game);

        this.accuracy = 0f;
        this.combo = 0;

        this.showScore = true;

        // change this if we want to add transition first
        danceMusic.playCurrentMusic(false);

        // add alarm and their strings
        resetAlarms();
    }

    public void draw()  {
        // render player
        player.draw(game.batch, game.assetManager);

        // render demo
        demo.draw(game.batch, game.assetManager);
    }

    public void update(float dt)    {
        if (danceMusic.isCurrentMusicPlaying()) {
            // checks hit accuracy
            updateAccuracyAndCombo();

        } else  {
            danceMusic.dispose();
            if (showScore) {
                if (shiftToScoreView()) {
                    updateToScoreView();
                }
            } else  {
                if (shiftToPlayView()) {
                    updateToMusicView();
                }
            }
        }

        // update player
        player.update(dt, danceMusic, game.gameCam);

        // update demo
        demo.update(dt, danceMusic, game.gameCam);
    }

    public void dispose()   {
        danceMusic.dispose();
        view.dispose();
    }

    // update accuracy and combo when player matches
    private void updateAccuracyAndCombo() {
        if (player.getArm(0).equalsIgnoreCase(demo.getArm(0)) && player.getArm(1).equalsIgnoreCase(demo.getArm(1))
                && demo.getMatchCurrent() == demo.getMatchPrev()) {
            demo.setHitMoveCount(demo.getHitMoveCount()+1);
            demo.setMatchPrev(demo.getMatchCurrent() * -1);
            combo += 1;
        } else  {
            combo = 0;
        }

        if (demo.getTotalMoveCount() != 0) {
            this.accuracy = demo.getHitMoveCount() / demo.getTotalMoveCount();
        }

        game.debugPlayInterface.setPlayerMatch(Float.toString(accuracy*100));
    }

    // updates to score view
    private void updateToScoreView()    {
        // sets interface for showing scores
        view.setInterfaceType(2);
        game.alarm.reduceTimer(SCORE_ALARM, 1);

        // after score alarm is up, show score
        if (game.alarm.isTimerDone(SCORE_ALARM)) {
            game.debugScoreInterface.setScoreString("Score!");
            game.alarm.reduceTimer(ACCURACY_ALARM, 1);
        }

        // after accuracy alarm is up, show accuracy
        if (game.alarm.isTimerDone(ACCURACY_ALARM)) {
            game.debugScoreInterface.setAccuracyString(Float.toString(accuracy));
            game.alarm.reduceTimer(TAP_ALARM, 1);
        }

        // when tap alarm is up, allow tap
        if (game.alarm.isTimerDone(TAP_ALARM))    {
            // change input to score interface
            Gdx.input.setInputProcessor(view.getGame().debugScoreInterface.getStage());

            // make it possible to tap when done
            showScore = !view.getGame().debugScoreInterface.isTapped();
        }
    }

    // updates to music view
    private void updateToMusicView()   {
        view.setInterfaceType(1);
        Gdx.input.setInputProcessor(view.getGame().debugPlayInterface.getStage());

        // resets demo hit and accuracy count
        resetCount();

        // reset alarm values
        resetAlarms();

        // rest score values
        game.debugScoreInterface.setScoreString("");
        game.debugScoreInterface.setAccuracyString("");

        danceMusic.playCurrentMusic(false);
        showScore = true;
    }

    // shift to score view
    private boolean shiftToScoreView() {
        if(view.getPlayBackground().getXCoordinate() < ApplicationConstants.WIDTH) {
            view.getPlayBackground().moveXCoordinate(SHIFT_RATE);
            view.setInterfaceType(0);
            return false;
        } else  {
            return true;
        }
    }

    // shift to play view
    private boolean shiftToPlayView() {
        if (view.getPlayBackground().getXCoordinate() > 0)  {
            view.getPlayBackground().moveXCoordinate(-SHIFT_RATE);
            view.setInterfaceType(0);
            return false;
        } else  {
            return true;
        }
    }

    private void resetCount()    {
        demo.resetCount();
        accuracy = 0;
    }

    private void resetAlarms() {
        game.alarm.setTimer(SCORE_ALARM, ALARM_RATE);
        game.alarm.setTimer(ACCURACY_ALARM, ALARM_RATE);
        game.alarm.setTimer(TAP_ALARM, ALARM_RATE);
    }
}
