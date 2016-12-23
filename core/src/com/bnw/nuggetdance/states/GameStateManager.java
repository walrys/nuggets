package com.bnw.nuggetdance.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    //Methods to manage stack
    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose(); //return pop and dispose
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){ //to keep looking at the top of GSM stack
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){ //keeps rendering sb of current state
        states.peek().render(sb);
    }
}
