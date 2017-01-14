package com.bnw.nuggetdance.Screens.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bnw.nuggetdance.Constants.ApplicationConstants;

/**
 * Created by Brandon on 14/1/17.
 */

public class PlayInterface {
    private Stage stage;
    private Viewport viewPort;

    private Label combo;

    private Table comboTable;
    private BitmapFont comboFont;
    private Label.LabelStyle debugLabelStyle;

    public PlayInterface(SpriteBatch sb, FreeTypeFontGenerator fontGenerator, FreeTypeFontGenerator.FreeTypeFontParameter parameter) {
        this.viewPort = new FitViewport(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewPort, sb);

        parameter.size = 100;
        comboFont = fontGenerator.generateFont(parameter);

        // define labels
        this.debugLabelStyle = new Label.LabelStyle(comboFont, Color.BLACK);
        this.combo = new Label("0", debugLabelStyle);

        // define table
        this.comboTable= new Table();

        // add combo score in
        this.comboTable.top();
        this.comboTable.setFillParent(true);
        this.comboTable.add(combo).pad(20, 350, 0, 0);

        stage.addActor(comboTable);
    }

    public boolean render()  {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
        return true;
    }

    public void dispose()   {
        stage.dispose();
    }

    public String setComboScore(int combo) {
        this.combo.setText(Integer.toString(combo));
        return this.combo.getText().toString();
    }

    public Label getCombo() {
        return combo;
    }
}
