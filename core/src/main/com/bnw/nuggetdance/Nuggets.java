package com.bnw.nuggetdance;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bnw.nuggetdance.Constants.AssetConstants;
import com.bnw.nuggetdance.Screens.MainScreen;

public class Nuggets extends Game {
    public SpriteBatch batch;
	public AssetManager assetManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		assetManager = new AssetManager();
		assetManager.load(AssetConstants.BACKGROUND_MAIN_1, Texture.class);
		assetManager.load(AssetConstants.BACKGROUND_PLAY_1, Texture.class);
		assetManager.load(AssetConstants.BACKGROUND_PLAY_2, Texture.class);
		assetManager.finishLoading();

		setScreen(new MainScreen(this, assetManager));
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		batch.dispose();
	}

	@Override
	public void render () {
        //use screen's render method
		super.render();
	}
}
