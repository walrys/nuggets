package com.bnw.nuggetdance.Screens.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bnw.nuggetdance.Backgrounds.PlayBackground;
import com.bnw.nuggetdance.Nuggets;

/**
 * Created by Walrus on 12/23/2016.
 */

public class PlayScreenView implements Screen {
    private Nuggets game;

    private PlayBackground playBackGround;
    private PlayScreenModel model;

    private int interfaceType;


    public PlayScreenView(Nuggets game)  {
        this.game = game;
        this.playBackGround = new PlayBackground(game.assetManager, game.gameCam);
        this.model = new PlayScreenModel(game, this);
        this.interfaceType = 1;
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


        // calls draw on model
        model.draw();

        // render interface
        pickInterfaceToRender();

        // update game camera
        game.gameCam.update();
    }


    public void update(float dt)    {
        // calls update on model
        model.update(dt);

        // handle user input first

        // handles all debug controls
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
        playBackGround.dispose();
    }

    public void pickInterfaceToRender()    {
        switch (interfaceType)  {
            case 0:
            break;

            case 1:
            game.debugPlayInterface.render();
            break;

            case 2:
            game.debugScoreInterface.render();
            break;
        }
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

    /*
     * user interface type 0: no view
     * user interface type 1: play view
     * user interface type 1: score view
     */
    public void setInterfaceType(int interfaceType)  {
        this.interfaceType = interfaceType;
    }
}
