package com.bnw.nuggetdance.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.bnw.nuggetdance.Constants.ApplicationConstants;

import java.util.ArrayList;

/**
 * Created by Brandon on 26/12/16.
 */

public class InterfaceDebug {
    protected static ArrayList<String> labelStrings;
    protected static Label currentRightButtonPosition;
    protected static Label currentLeftButtonPosition;
    private Label demoLeftPosition;
    private Label demoRightPosition;

    protected static int currentLeftArmPosition;
    protected static int currentRightArmPosition;
    private Stage stage;
    private Viewport viewPort;
    private Table controlsTableTop;
    private Table controlsTableCentre;
    private Table controlsTableBottom;
    private Table debugTableInfo;

    private Skin skin;
    private TextureAtlas buttonTexture;
    private ArrayList<TextButton> leftButtons;
    private ArrayList<TextButton> rightButtons;
    private TextButton.TextButtonStyle buttonStyle;
    private BitmapFont font;
    private Label.LabelStyle debugLabelStyle;

    private FPSLogger fps = new FPSLogger();


    public InterfaceDebug(SpriteBatch sb) {
        this.viewPort = new FitViewport(ApplicationConstants.WIDTH, ApplicationConstants.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewPort, sb);
        this.controlsTableTop = new Table();
        this.controlsTableCentre = new Table();
        this.controlsTableBottom = new Table();
        this.debugTableInfo = new Table();

        this.skin = new Skin();
        this.buttonStyle = new com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle();
        this.buttonTexture = new TextureAtlas(Gdx.files.internal("art/UI/uiskin.atlas"));

        // define label strings
        this.labelStrings = new ArrayList<String>();
        labelStrings.add("NONE");
        labelStrings.add("WEST");
        labelStrings.add("EAST");
        labelStrings.add("NORTH");
        labelStrings.add("SOUTH");
        labelStrings.add("NORTHWEST");
        labelStrings.add("NORTHEAST");
        labelStrings.add("SOUTHWEST");
        labelStrings.add("SOUTHEAST");

        // define label
        this.font = new BitmapFont();
        this.debugLabelStyle = new Label.LabelStyle(font, Color.BLACK);
        this.currentRightButtonPosition = new Label(labelStrings.get(0), debugLabelStyle);
        this.currentLeftButtonPosition = new Label(labelStrings.get(0), debugLabelStyle);

        this.demoLeftPosition = new Label("NONE", debugLabelStyle);
        this.demoRightPosition = new Label("NONE", debugLabelStyle);

        // adding a button style
        this.skin.addRegions(buttonTexture);
        this.buttonStyle.font = font;
        this.buttonStyle.up = skin.getDrawable("default-round");
        //this.buttonStyle.down = skin.getDrawable("default-round-down");

        // add left side buttons
        this.leftButtons = new ArrayList<TextButton>();
        leftButtons.add(new TextButton("WEST", buttonStyle));
        leftButtons.add(new TextButton("EAST", buttonStyle));
        leftButtons.add(new TextButton("NORTH", buttonStyle));
        leftButtons.add(new TextButton("SOUTH", buttonStyle));
        leftButtons.add(new TextButton("NORTH WEST", buttonStyle));
        leftButtons.add(new TextButton("NORTH EAST", buttonStyle));
        leftButtons.add(new TextButton("SOUTH WEST", buttonStyle));
        leftButtons.add(new TextButton("SOUTH EAST", buttonStyle));

        // add right side buttons
        this.rightButtons = new ArrayList<TextButton>();
        rightButtons.add(new TextButton("WEST", buttonStyle));
        rightButtons.add(new TextButton("EAST", buttonStyle));
        rightButtons.add(new TextButton("NORTH", buttonStyle));
        rightButtons.add(new TextButton("SOUTH", buttonStyle));
        rightButtons.add(new TextButton("NORTH WEST", buttonStyle));
        rightButtons.add(new TextButton("NORTH EAST", buttonStyle));
        rightButtons.add(new TextButton("SOUTH WEST", buttonStyle));
        rightButtons.add(new TextButton("SOUTH EAST", buttonStyle));

        // modify button size
        for (int i=0;i<leftButtons.size();i++)  {
            leftButtons.get(i).getLabel().setWrap(true);
            leftButtons.get(i).getLabel().setWidth(100);
        }

        for (int i=0;i<rightButtons.size();i++)  {
            rightButtons.get(i).getLabel().setWrap(true);
            rightButtons.get(i).getLabel().setWidth(100);
        }

        // add listener
        for (int i=0; i<leftButtons.size();i++) {
            leftButtons.get(i).addListener(new DirectionListenerDebug(leftButtons.get(i), i + 1, 0));
        }

        for (int i=0; i<rightButtons.size();i++) {
            rightButtons.get(i).addListener(new DirectionListenerDebug(rightButtons.get(i), i + 1, 1));
        }

        /*
         * Create Table
         */

        //this.controlsTableTop.debugAll();
        this.controlsTableTop.bottom();
        this.controlsTableTop.setFillParent(true);

        this.controlsTableTop.add(leftButtons.get(4).pad(5, 5, 5, 5)).pad(10, 1, 170, 1).width(77.5f);
        this.controlsTableTop.add(leftButtons.get(2).pad(5, 5, 5, 5)).pad(10, 1, 170, 1).width(77.5f);
        this.controlsTableTop.add(leftButtons.get(5).pad(5, 5, 5, 5)).pad(10, 1, 170, 1).width(77.5f);
        this.controlsTableTop.add(rightButtons.get(4).pad(5, 5, 5, 5)).pad(10, 1, 170, 1).width(77.5f);
        this.controlsTableTop.add(rightButtons.get(2).pad(5, 5, 5, 5)).pad(10, 1, 170, 1).width(77.5f);
        this.controlsTableTop.add(rightButtons.get(5).pad(5, 5, 5, 5)).pad(10, 1, 170, 1).width(77.5f);

        //this.controlsTableCentre.debugAll();
        this.controlsTableCentre.bottom();
        this.controlsTableCentre.setFillParent(true);
        this.controlsTableCentre.add(leftButtons.get(0).pad(5, 5, 5, 5)).pad(10, 0, 120, 42).width(77.5f);
        this.controlsTableCentre.add(leftButtons.get(1).pad(5, 5, 5, 5)).pad(10, 42, 120, 0).width(77.5f);
        this.controlsTableCentre.add(rightButtons.get(0).pad(5, 5, 5, 5)).pad(10, 0, 120, 42).width(77.5f);
        this.controlsTableCentre.add(rightButtons.get(1).pad(5, 5, 5, 5)).pad(10, 42, 120, 0).width(77.5f);

        //this.controlsTableBottom.debugAll();
        this.controlsTableBottom.bottom();
        this.controlsTableBottom.setFillParent(true);
        this.controlsTableBottom.add(leftButtons.get(6).pad(5, 5, 5, 5)).pad(10, 1, 60, 1).width(77.5f);
        this.controlsTableBottom.add(leftButtons.get(3).pad(5, 5, 5, 5)).pad(10, 1, 60, 1).width(77.5f);
        this.controlsTableBottom.add(leftButtons.get(7).pad(5, 5, 5, 5)).pad(10, 1, 60, 1).width(77.5f);
        this.controlsTableBottom.add(rightButtons.get(6).pad(5, 5, 5, 5)).pad(10, 1, 60, 1).width(77.5f);
        this.controlsTableBottom.add(rightButtons.get(3).pad(5, 5, 5, 5)).pad(10, 1, 60, 1).width(77.5f);
        this.controlsTableBottom.add(rightButtons.get(7).pad(5, 5, 5, 5)).pad(10, 1, 60, 1).width(77.5f);

        this.debugTableInfo.debugAll();
        this.debugTableInfo.top();
        this.debugTableInfo.setFillParent(true);
        this.debugTableInfo.add(currentLeftButtonPosition).pad(300, 0, 0, 100);
        this.debugTableInfo.add(currentRightButtonPosition).pad(300, 100, 0, 0);

        this.debugTableInfo.row();

        this.debugTableInfo.add(demoLeftPosition).pad(100, 0, 0, 100);
        this.debugTableInfo.add(demoRightPosition).pad(100, 100, 0, 0);

        stage.addActor(this.controlsTableTop);
        stage.addActor(this.controlsTableCentre);
        stage.addActor(this.controlsTableBottom);
        stage.addActor(this.debugTableInfo);
    }

    public boolean render()  {
        // print fps every second
        //fps.log();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
        return true;
    }

    public void dispose()   {
        stage.dispose();
        skin.dispose();
        buttonTexture.dispose();
    }

    public Stage getStage()  {
        return stage;
    }

    public Label getDemoLeftLabel() {
        return demoLeftPosition;
    }

    public Label getDemoRightLabel()  {
        return demoRightPosition;
    }

    public static void setPlayerCurrentArmPosition(int position, int arm){
        if(arm == 0){
            currentLeftArmPosition = position;
        }
        else {
            currentRightArmPosition = position;
        }
    }
}
