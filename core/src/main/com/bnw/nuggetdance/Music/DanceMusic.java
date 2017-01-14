package com.bnw.nuggetdance.Music;

import com.badlogic.gdx.audio.Music;
import com.bnw.nuggetdance.Constants.AssetConstants;
import com.bnw.nuggetdance.Misc.Metronome.Metronome;
import com.bnw.nuggetdance.Nuggets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Brandon on 29/12/16.
 */

public class DanceMusic {

    private HashMap<Integer, Music> danceMusic;
    private ArrayList<Integer> bpmList;
    private int currentMusic;
    private Random randomizer;
    private Metronome metronome;
    private float moveTracker;
    private long prevBeat;

    public DanceMusic(Nuggets game) {
        this.danceMusic = new HashMap<Integer, Music>();
        this.bpmList = new ArrayList<Integer>();
        this.randomizer = new Random();
        this.moveTracker = 0f;
        this.metronome = new Metronome();
        this.prevBeat = 0;

        // add dance music into game
        this.danceMusic.put(0, game.assetManager.get(AssetConstants.MUSIC_SOUL_BOSSA, Music.class));
        this.bpmList.add(147);

        this.currentMusic = 0;
    }

    public void playCurrentMusic(boolean isLoop)  {
        danceMusic.get(currentMusic).setLooping(isLoop);

        danceMusic.get(currentMusic).play();
        //currentMusic.setPosition(200);

        metronome.setBpm(bpmList.get(0));
        metronome.start();
    }


    public void stopCurrentMusic()  {
        danceMusic.get(currentMusic).stop();
        metronome.stop();
    }

    public void checkStopMusic()   {
        if (danceMusic.get(currentMusic).getPosition() >= 56 && isCurrentMusicPlaying())  {
            stopCurrentMusic();
        }
    }

    public boolean isCurrentMusicPlaying()   {
        return danceMusic.get(currentMusic).isPlaying();
    }

    public long getBeat()    {
        return metronome.getLastBeatCount();
    }

    public boolean isCorrectBeat() {
        if ((metronome.getLastBeatCount()+1) % 4 == 0)  {
            if (prevBeat == metronome.getLastBeatCount()+1)   {
                return false;
            } else  {
                prevBeat = metronome.getLastBeatCount()+1;
                return true;
            }
        }

        return false;
    }

    public void dispose()   {
        metronome.stop();
    }
}
