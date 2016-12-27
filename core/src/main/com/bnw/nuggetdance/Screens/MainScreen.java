package com.bnw.nuggetdance.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bnw.nuggetdance.Backgrounds.MainBackground;
import com.bnw.nuggetdance.Nuggets;

/**
 * Created by Walrus on 12/23/2016.
 */

public class MainScreen implements Screen {
    private Nuggets game;
    private MainBackground mainBackground;

    public MainScreen(Nuggets game) {
        this.game = game;

        mainBackground = new MainBackground(game.assetManager, game.gameCam);
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

        // set projection matrix
        game.batch.setProjectionMatrix(game.gameCam.combined);

        // draw background image
        mainBackground.draw(game.batch);

        // update game camera
        game.gameCam.update();
    }

    public void update(float dt){
        //handle user input first
        game.debugInputHandler.handleTouchInput(dt, this);
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
}
