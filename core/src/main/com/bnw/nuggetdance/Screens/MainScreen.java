package com.bnw.nuggetdance.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.bnw.nuggetdance.Backgrounds.MainBackground;
import com.bnw.nuggetdance.Debug.InputHandlerDebug;
import com.bnw.nuggetdance.Nuggets;

/**
 * Created by Walrus on 12/23/2016.
 */

public class MainScreen implements Screen {
    Nuggets game;
    AssetManager assetManager;

    MainBackground mainBackground;
    InputHandlerDebug debugInputHandler;

    public MainScreen(Nuggets game, AssetManager assetManager){
        this.game = game;
        this.assetManager = assetManager;

        mainBackground = new MainBackground(this.assetManager);

        debugInputHandler = new InputHandlerDebug();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw background image
        mainBackground.draw(game.batch);
    }

    public void update(float dt){
        //handle user input first
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
        mainBackground.dispose();
    }

    /*
     * Getters
     */

    public Nuggets getGame()   {
        return game;
    }

    public MainBackground getMainBackground()    {
        return mainBackground;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
