package com.bnw.nuggetdance.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bnw.nuggetdance.Backgrounds.PlayBackground;
import com.bnw.nuggetdance.Debug.PlayerDebug;
import com.bnw.nuggetdance.Nuggets;

/**
 * Created by Walrus on 12/23/2016.
 */

public class PlayScreen implements Screen {
    private Nuggets game;

    private PlayBackground playBackGround;
    private PlayerDebug player;


    public PlayScreen(Nuggets game)  {
        this.game = game;
        this.playBackGround = new PlayBackground(game.assetManager, game.gameCam);

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

        // draw play background
        playBackGround.draw(game.batch);

        // draw debug
        game.debugInterface.draw();
        player.draw(game.batch);

        // update game camera
        game.gameCam.update();
    }


    public void update(float dt){
        //handle user input first

        // handles all debug controls
        game.debugInputHandler.handleTouchInput(dt, this);
        player.update(dt);
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
