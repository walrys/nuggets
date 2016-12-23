package com.bnw.nuggetdance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Walrus on 12/23/2016.
 */

public class MainScreen implements Screen {
    Nuggets game;
    Texture mainsketch;

    public MainScreen(Nuggets game){
        this.game = game;
        mainsketch = new Texture("sketch_main.JPG");
    }
    @Override
    public void show() {

    }

    protected void handleInput(float dt) {
        if(Gdx.input.isTouched()){
            game.setScreen(new PlayScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
            game.batch.draw(mainsketch,0,0);
        game.batch.end();
    }

    public void update(float dt){
        //handle user input first
        handleInput(dt);
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

    }
}
