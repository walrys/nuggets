package com.bnw.nuggetdance.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.bnw.nuggetdance.Backgrounds.PlayBackground;
import com.bnw.nuggetdance.Debug.InputHandlerDebug;
import com.bnw.nuggetdance.Nuggets;

/**
 * Created by Walrus on 12/23/2016.
 */

public class PlayScreen implements Screen {
    private Nuggets game;
    private AssetManager assetManager;

    private PlayBackground playBackGround;

    private InputHandlerDebug debugInputHandler;

    public PlayScreen(Nuggets game, AssetManager assetManager)  {
        this.game = game;
        this.assetManager = assetManager;

        this.playBackGround = new PlayBackground(this.assetManager);

        this.debugInputHandler = new InputHandlerDebug();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw play background
        playBackGround.draw(game.batch);
    }


    public void update(float dt){
        //handle user input first

        // handles all debug controls
        debugInputHandler.handleTouchInput(dt, this);
    }

    @Override
    public void resize(int width, int height) {

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

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
