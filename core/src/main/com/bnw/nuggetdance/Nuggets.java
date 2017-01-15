package com.bnw.nuggetdance;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Constants.AssetConstants;
import com.bnw.nuggetdance.Debug.InputHandlerDebug;
import com.bnw.nuggetdance.Debug.Interface.InterfacePlayDebug;
import com.bnw.nuggetdance.Debug.Interface.InterfaceScoreDebug;
import com.bnw.nuggetdance.Misc.Others.Timer;
import com.bnw.nuggetdance.Misc.SFX.SFXHandler;
import com.bnw.nuggetdance.Screens.Box2dScreen;
import com.bnw.nuggetdance.Screens.MainScreen;

public class Nuggets extends Game {
    public SpriteBatch batch;
	public AssetManager assetManager;
	public OrthographicCamera gameCam;
	public Viewport gamePort;

	public FreeTypeFontGenerator fontGenerator;
	public FreeTypeFontParameter parameter;
	public SFXHandler sfx;

	public InputHandlerDebug debugInputHandler;
	public InterfacePlayDebug debugPlayInterface;
    public InterfaceScoreDebug debugScoreInterface;
	public Timer alarm;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameCam = new OrthographicCamera();
		gamePort = new FillViewport(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, gameCam);

		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Digitalt.ttf"));
		parameter = new FreeTypeFontParameter();

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
		assetManager.load(AssetConstants.SPR_CIRCLE, Texture.class);
		assetManager.load(AssetConstants.SPR_CROSS, Texture.class);

		assetManager.load(AssetConstants.MUSIC_SOUL_BOSSA, Music.class);
		assetManager.finishLoading();

		// create alarm object
		alarm = new Timer();

		// create sfx object
		sfx = new SFXHandler();

		// create debug play interface
		debugPlayInterface = new InterfacePlayDebug(batch);

		// create debug score interface
        debugScoreInterface = new InterfaceScoreDebug(assetManager, batch, fontGenerator, parameter);

		// create debug input handler
		debugInputHandler = new InputHandlerDebug(debugPlayInterface);

		//setScreen(new Box2dScreen(this));
		setScreen(new MainScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		batch.dispose();
		debugPlayInterface.dispose();
		fontGenerator.dispose();
	}

	@Override
	public void render () {
        //use screen's render method
		super.render();
	}

}
