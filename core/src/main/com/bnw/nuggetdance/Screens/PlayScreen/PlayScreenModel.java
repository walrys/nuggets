package com.bnw.nuggetdance.Screens.PlayScreen;

import com.bnw.nuggetdance.Debug.PlayerDebug;
import com.bnw.nuggetdance.Music.DanceMusic;
import com.bnw.nuggetdance.Nuggets;
import com.bnw.nuggetdance.Sprites.DemonstratorNugget;

/**
 * Created by Brandon on 6/1/17.
 */

public class PlayScreenModel {

    private Nuggets game;
    private com.bnw.nuggetdance.Screens.PlayScreen.PlayScreenView view;
    private PlayerDebug player;
    private DemonstratorNugget demo;
    private DanceMusic danceMusic;
    private int matchCount;


    public PlayScreenModel(Nuggets game, com.bnw.nuggetdance.Screens.PlayScreen.PlayScreenView view)    {
        this.game = game;
        this.view = view;

        this.demo = new DemonstratorNugget(this.game);
        this.player = new PlayerDebug(game, game.assetManager);
        this.danceMusic = new DanceMusic(game);

        this.matchCount = 0;
    }

    public void draw()  {
        // render player
        player.draw(game.batch, game.assetManager);

        // render demo
        demo.draw(game.batch, game.assetManager);
    }

    public void update(float dt)    {
        if (!danceMusic.isCurrentMusicPlaying()) {
            danceMusic.randomizeMusic();
            danceMusic.playCurrentMusic(false);
        }

        // checks for matching
        checkMatch();

        // update player
        player.update(dt, danceMusic, game.gameCam);

        // update demo
        demo.update(dt, danceMusic, game.gameCam);

    }

    public void checkMatch() {
        if (player.getArm(0).equalsIgnoreCase(demo.getArm(0)) && player.getArm(1).equalsIgnoreCase(demo.getArm(1))
                && demo.getMatchCurrent() == demo.getMatchPrev()) {
            matchCount += 1;
            game.debugInterface.setPlayerMatch(Integer.toString(matchCount));
            demo.setMatchPrev(demo.getMatchCurrent() * -1);
        }
    }

    public int getMatchCount()  {
        return matchCount;
    }

    public void dispose()   {

    }
}
