package com.bnw.nuggetdance.Debug.Interface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Misc.SFX.SFXHandler;
import com.bnw.nuggetdance.Sprites.Circle;
import com.bnw.nuggetdance.Sprites.Cross;

import java.util.Random;

/**
 * Created by Brandon on 8/1/17.
 */

public class InterfaceScoreDebug {
    private Stage stage;
    private Viewport viewPort;
    private AssetManager assetManager;

    private Table scoreTable;
    private Table accuracyTable;

    private BitmapFont font;
    private Label.LabelStyle debugLabelStyle;

    private Label score;
    private Label tapped;
    private float tappedAlpha;

    private boolean hasTapped;

    public InterfaceScoreDebug(AssetManager assetManager, SpriteBatch sb, FreeTypeFontGenerator fontGenerator, FreeTypeFontParameter parameter) {
        this.viewPort = new FitViewport(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewPort, sb);
        this.assetManager = assetManager;

        this.scoreTable = new Table();
        this.accuracyTable = new Table();

        parameter.size = 150;
        this.font = fontGenerator.generateFont(parameter);

        this.debugLabelStyle = new Label.LabelStyle(font, Color.BLACK);

        this.score = new Label("", debugLabelStyle);
        this.tapped = new Label("", debugLabelStyle);
        tapped.setWrap(false);
        tapped.setFontScale(0.2f);
        this.tappedAlpha = 1;

        this.scoreTable.top();
        this.scoreTable.setFillParent(true);
        this.scoreTable.add(score).pad(100, 0, 0, 0);
        this.scoreTable.row();
        this.scoreTable.add(tapped).pad(400, 0, 0, 0);

        this.hasTapped = false;

        this.accuracyTable.top();
        this.accuracyTable.setFillParent(true);

        stage.addActor(scoreTable);
        stage.addActor(accuracyTable);
    }

    private void generateRandomAsset(int type)   {
        Random rng = new Random();
        switch (type)   {
            case 0:
                for (int i=0; i < 4; i++)   {
                    accuracyTable.add(new Cross(assetManager)).pad(250, 0, 0, 0);
                }
                break;

            case 1:
                int p1 = rng.nextInt(4);
                for (int i = 0; i < 4; i++) {
                    if (i == p1) {
                        accuracyTable.add(new Circle(assetManager)).pad(250, 0, 0, 0);
                    } else  {
                        accuracyTable.add(new Cross(assetManager)).pad(250, 0, 0, 0);
                    }
                }
                break;

            case 2:
                int p2_1 = rng.nextInt(4);
                int p2_2 = 0;
                do {
                    p2_2 = rng.nextInt(4);
                } while (p2_2 == p2_1);

                for (int i=0; i<4; i++) {
                    if (i == p2_1 || i == p2_2)   {
                        accuracyTable.add(new Circle(assetManager)).pad(250, 0, 0, 0);
                    } else  {
                        accuracyTable.add(new Cross(assetManager)).pad(250, 0, 0, 0);
                    }
                }
                break;

            case 3:
                int p3 = rng.nextInt(4);
                for (int i=0; i<4 ;i++)   {
                    if (i == p3)    {
                        accuracyTable.add(new Cross(assetManager)).pad(250, 0, 0, 0);
                    } else  {
                        accuracyTable.add (new Circle(assetManager)).pad(250, 0, 0, 0);
                    }
                }
                break;

            case 4:
                for (int i=0;i<4;i++)   {
                    accuracyTable.add(new Circle(assetManager)).pad(250, 0, 0, 0);
                }
                break;
        }
    }

    public boolean render() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
        return true;
    }

    public boolean isTapped() {
        if (Gdx.input.isTouched()) {
            return true;
        }
        return false;
    }

    public void clearAllAccuracyChildren()   {
        accuracyTable.clearChildren();
    }

    public Stage getStage() {
        return stage;
    }

    public Label getScore() {
        return score;
    }

    public Label getTapped()    {
        return tapped;
    }

    public boolean getHasTapped()  {
        return hasTapped;
    }

    public void setHasTapped(boolean hasTapped)  {
        this.hasTapped = hasTapped;
    }

    public void setScoreString(String scoreString)    {
        score.setText(scoreString);
    }

    public void setTappedString(String tappedString, SFXHandler sfxHandler)    {
        if (tappedAlpha < 0)    {
            tappedAlpha = 1;
        }
        tappedAlpha = sfxHandler.fadeText(-0.02f, tappedAlpha, tapped);
        tapped.setText(tappedString);
    }

    public void setTappedAlpha(float alpha) {
        tappedAlpha = alpha;
    }

    public void setAccuracy(float accuracy) {
        if (accuracy <= 0f) {
            generateRandomAsset(0);
        } else if (accuracy < 25f) {
            generateRandomAsset(1);
        } else if (accuracy < 50f)  {
            generateRandomAsset(2);
        } else if (accuracy < 75f)  {
            generateRandomAsset(3);
        } else if (accuracy >= 100f)    {
            generateRandomAsset(4);
        }
    }
}
