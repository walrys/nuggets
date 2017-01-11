package com.bnw.nuggetdance.Debug.Interface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bnw.nuggetdance.Constants.ApplicationConstants;

/**
 * Created by Brandon on 8/1/17.
 */

public class InterfaceScoreDebug {
    private Stage stage;
    private Viewport viewPort;
    private Table scoreTable;
    private BitmapFont font;
    private Label.LabelStyle debugLabelStyle;

    private Label score;
    private Label accuracy;

    private boolean hasTapped;

    public InterfaceScoreDebug(SpriteBatch sb) {
        this.viewPort = new FitViewport(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewPort, sb);
        this.scoreTable = new Table();

        this.font = new BitmapFont();
        this.debugLabelStyle = new Label.LabelStyle(font, Color.BLACK);

        this.score = new Label("", debugLabelStyle);
        this.accuracy = new Label("", debugLabelStyle);

        this.scoreTable.top();
        this.scoreTable.setFillParent(true);
        this.scoreTable.add(score).pad(150, 0, 0, 0);
        this.scoreTable.row();
        this.scoreTable.add(accuracy).pad(100, 0, 0, 0);

        this.hasTapped = false;

        stage.addActor(scoreTable);
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

    public Stage getStage() {
        return stage;
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

    public void setAccuracyString(String accuracyString)    {
        accuracy.setText(accuracyString);
    }

}
