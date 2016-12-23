package com.bnw.nuggetdance;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Nuggets extends Game {
    public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new com.bnw.nuggetdance.Screens.MainScreen(this));
	}

	@Override
	public void render () {
        //use screen's render method
		super.render();
	}
}
