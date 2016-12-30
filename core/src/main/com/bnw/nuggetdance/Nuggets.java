package com.bnw.nuggetdance;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
		assetManager.load(AssetConstants.SPR_PLAYER_BONE, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_0, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_1, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_2, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_3, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_4, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_5, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_6, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_7, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_LEFT_8, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_0, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_1, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_2, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_3, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_4, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_5, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_6, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_7, Texture.class);
		assetManager.load(AssetConstants.SPR_BONE_ARM_RIGHT_8, Texture.class);
		assetManager.load(AssetConstants.SPR_DEMO_BODY, Texture.class);

		assetManager.load(AssetConstants.MUSIC_SOUL_BOSSA, Music.class);
		assetManager.finishLoading();

		debugInterface = new InterfaceDebug(batch);
		debugInputHandler = new InputHandlerDebug(debugInterface);

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
