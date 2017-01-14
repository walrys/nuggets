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
    private String VISIBILITY_ALARM = "Visible";
    private int ALARM_RATE = 60;
    private int SHIFT_RATE = 15;
    private int BASE_SCORE = 10;

    private boolean isAccuracySet = false;

    private Nuggets game;
    private PlayScreenView view;
    private PlayerDebug player;
    private DemonstratorNugget demo;
    private DanceMusic danceMusic;

    private float accuracy;
    private long score;
    private int index;

    private long calculatedScore;

    private boolean showScore;

    public PlayScreenModel(Nuggets game, PlayScreenView view)    {
        this.game = game;
        this.view = view;

        this.demo = new DemonstratorNugget(this.game);
        this.player = new PlayerDebug(game, game.assetManager);
        this.danceMusic = new DanceMusic(game);

        this.score = 9999999;
        this.accuracy = 0f;
        this.index = 0;

        this.calculatedScore = 9999999;

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

        // update music
        danceMusic.checkStopMusic();
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
            demo.increaseCombo(1);
            view.getPlayInterface().getCombo().setFontScale(1.2f);
            score += BASE_SCORE * demo.getTimeMultipler() * demo.getCurrentCombo();
        }

        game.sfx.reduceSize(view.getPlayInterface().getCombo(), 0.05f);

        if (demo.getTotalMoveCount() != 0) {
            this.accuracy = demo.getHitMoveCount() / demo.getTotalMoveCount();
        }

        game.debugPlayInterface.setPlayerMatch(Float.toString(accuracy*100));
        game.debugPlayInterface.setComboString(Integer.toString(demo.getCurrentCombo()));

        // update combo view
        view.getPlayInterface().setComboScore(demo.getCurrentCombo());
    }

    // updates to score view
    private void updateToScoreView()    {
        // sets interface for showing scores
        view.setInterfaceType(2);
        game.alarm.reduceTimer(SCORE_ALARM, 1);

        // after score alarm is up, show score
        if (game.alarm.isTimerDone(SCORE_ALARM)) {
            if (score != calculatedScore) {
                calculateScore();

                // make score size bigger
                if (score == calculatedScore)   {
                    game.debugScoreInterface.getScore().setFontScale(1.2f);
                }
            }

            // reduce size if too big
            game.sfx.reduceSize(game.debugScoreInterface.getScore(), 0.02f);

            game.debugScoreInterface.setScoreString(Long.toString(calculatedScore));
            game.alarm.reduceTimer(ACCURACY_ALARM, 1);
        }

        // after accuracy alarm is up, show accuracy
        if (game.alarm.isTimerDone(ACCURACY_ALARM)) {
            if (!isAccuracySet) {
                game.debugScoreInterface.setAccuracy(accuracy);
                isAccuracySet = true;
            }

            if (game.alarm.isTimerDone(VISIBILITY_ALARM) && index < 4) {
                game.debugScoreInterface.setAccuracyVisibility(index);
                index += 1;

                game.alarm.setTimer(VISIBILITY_ALARM, ALARM_RATE - 30);
            }

            game.alarm.reduceTimer(VISIBILITY_ALARM, 1);
            game.alarm.reduceTimer(TAP_ALARM, 1);
        }

        // when tap alarm is up, allow tap
        if (game.alarm.isTimerDone(TAP_ALARM))    {
            // change input to score interface
            Gdx.input.setInputProcessor(view.getGame().debugScoreInterface.getStage());
            game.debugScoreInterface.setTappedString("Tap screen to continue", game.sfx);

            // make it possible to tap when done
            showScore = !view.getGame().debugScoreInterface.isTapped();
        }
    }

    // updates to music view
    private void updateToMusicView()   {
        view.setInterfaceType(1);
        Gdx.input.setInputProcessor(view.getGame().debugPlayInterface.getStage());

        // resets hit and calculated score
        resetScore();

        // reset alarm values
        resetAlarms();

        // reset score screen strings
        resetStrings();

        // reset accuracy
        resetAccuracy();

        danceMusic.playCurrentMusic(false);
        showScore = true;
    }

    private void calculateScore()    {
        if (score - calculatedScore < 10)   {
            calculatedScore += 1;
        } else if (score - calculatedScore < 100)   {
            calculatedScore += 9;
        } else if (score - calculatedScore < 1000)  {
            calculatedScore += 99;
        } else if (score - calculatedScore < 10000) {
            calculatedScore += 999;
        }
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

    private void resetScore()    {
        demo.resetCount();
        calculatedScore = 0;
    }

    private void resetAlarms() {
        game.alarm.setTimer(SCORE_ALARM, ALARM_RATE);
        game.alarm.setTimer(ACCURACY_ALARM, ALARM_RATE);
        game.alarm.setTimer(TAP_ALARM, ALARM_RATE * 3);
        game.alarm.setTimer(VISIBILITY_ALARM, ALARM_RATE - 30);
        index = 0;
    }

    private void resetStrings() {
        game.debugScoreInterface.setScoreString("");
        game.debugScoreInterface.setTappedString("", game.sfx);
        game.debugScoreInterface.setTappedAlpha(1);
    }

    private void resetAccuracy() {
        game.debugScoreInterface.clearAllAccuracyChildren();
        accuracy = 0;
        isAccuracySet = false;
    }
}
