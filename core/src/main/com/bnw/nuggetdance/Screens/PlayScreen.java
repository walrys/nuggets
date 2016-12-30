package com.bnw.nuggetdance.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bnw.nuggetdance.Backgrounds.PlayBackground;
import com.bnw.nuggetdance.Music.DanceMusic;
import com.bnw.nuggetdance.Debug.PlayerDebug;
import com.bnw.nuggetdance.Nuggets;
import com.bnw.nuggetdance.Sprites.DemonstratorNugget;

/**
 * Created by Walrus on 12/23/2016.
 */

public class PlayScreen implements Screen {
    private Nuggets game;

    private PlayBackground playBackGround;
    private PlayerDebug player;

    private DemonstratorNugget demo;
    private DanceMusic danceMusic;


    public PlayScreen(Nuggets game)  {
        this.game = game;
        this.playBackGround = new PlayBackground(game.assetManager, game.gameCam);

        this.demo = new DemonstratorNugget(this.game);
        this.danceMusic = new DanceMusic(game);
        player = new PlayerDebug(game, game.assetManager);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set projection matrix
        game.batch.setProjectionMatrix(game.gameCam.combined);

        // render play background
        playBackGround.draw(game.batch);

        // render debug
        game.debugInterface.render();

        // render player
        player.draw(game.batch, game.assetManager);

        // render demo
        demo.draw(game.batch, game.assetManager);

        // update game camera
        game.gameCam.update();
    }


    public void update(float dt){
        // play music
        if (!danceMusic.isCurrentMusicPlaying()) {
            danceMusic.randomizeMusic();
            danceMusic.playCurrentMusic(false);
        }

        // handle user input first

        // handles all debug controls
        game.debugInputHandler.handleTouchInput(dt, this);

        // update player
        player.update(dt, danceMusic, game.gameCam);

        // update demo
        demo.update(dt, danceMusic, game.gameCam);
    }

    @Override
    public void resize(int width, int height) {
        game.gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        playBackGround.dispose();
    }

    /*
     * Getters
     */

    public Nuggets getGame()    {
        return game;
    }

    public PlayBackground getPlayBackground()  {
        return playBackGround;
    }
}
