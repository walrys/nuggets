package com.bnw.nuggetdance;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Constants.AssetConstants;
import com.bnw.nuggetdance.Debug.InputHandlerDebug;
import com.bnw.nuggetdance.Debug.InterfaceDebug;
import com.bnw.nuggetdance.Screens.MainScreen;

public class Nuggets extends Game {
    public SpriteBatch batch;
	public AssetManager assetManager;
	public OrthographicCamera gameCam;
	public Viewport gamePort;

	public InputHandlerDebug debugInputHandler;
	public InterfaceDebug debugInterface;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameCam = new OrthographicCamera();
		gamePort = new FitViewport(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, gameCam);

		assetManager = new AssetManager();
		assetManager.load(AssetConstants.BACKGROUND_MAIN_1, Texture.class);
		assetManager.load(AssetConstants.BACKGROUND_PLAY_1, Texture.class);
		assetManager.load(AssetConstants.BACKGROUND_PLAY_2, Texture.class);
		assetManager.finishLoading();

		debugInputHandler = new InputHandlerDebug();
		debugInterface = new InterfaceDebug(batch);

		setScreen(new MainScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		batch.dispose();
		debugInterface.dispose();
	}

	@Override
	public void render () {
        //use screen's render method
		super.render();
	}

}
