package com.bnw.nuggetdance;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Nuggets extends Game {
    public SpriteBatch batch;

	//Application Window setup
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Nugget Dance";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
        //use screen's render method
		super.render();
	}
}
