package com.bnw.nuggetdance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Walrus on 12/23/2016.
 */

public class PlayScreen implements Screen {
    private Nuggets game;
    Texture playsketch;
    Texture playsketch2;
    float x,y;

    public PlayScreen(Nuggets game){
        this.game = game;
        playsketch = new Texture("sketch_play.jpg");
        playsketch2 = new Texture("sketch_play2.jpg");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        game.batch.begin();
        game.batch.draw(playsketch,x,y);
        game.batch.draw(playsketch2,x+game.WIDTH,y);
        game.batch.end();
    }


    public void update(float dt){
        //handle user input first
        handleInput(dt);
    }


    protected void handleInput(float dt) {
        if(Gdx.input.isTouched()){
            if(x>-game.WIDTH)
            x-=10;
        }
        else if (x<0)
            x+=10;
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
