package com.bnw.nuggetdance.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager()   {
        states = new Stack<State>();
    }

    //Methods to manage stack
    public void push(State state)   {
        states.push(state);
    }

    //return pop and dispose
    public void pop()   {
        states.pop().dispose();
    }

    public void set(State state)    {
        states.pop().dispose();
        states.push(state);
    }

    //to keep looking at the top of GSM stack
    public void update(float dt)    {
        states.peek().update(dt);
    }

    //keeps rendering sb of current state
    public void render(SpriteBatch sb)  {
        states.peek().render(sb);
    }
}
