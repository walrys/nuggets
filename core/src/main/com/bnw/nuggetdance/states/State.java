package com.bnw.nuggetdance.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(){
    }

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();

    }

    /*
     * com.bnw.nuggetdance.States.State class methods
     */

    protected abstract void handleInput();

    //delta time: the difference between frames rendered
    public abstract void update(float dt);

    //sprite batch: container for everything that needs to be rendered
    public abstract void render(SpriteBatch sb);

    //"to be called when we transition between states"
    public abstract void dispose();
}
