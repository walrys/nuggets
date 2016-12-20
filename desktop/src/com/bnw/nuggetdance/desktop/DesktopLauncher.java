package com.bnw.nuggetdance.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bnw.nuggetdance.Nuggets;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Nuggets.WIDTH;     //added in tutorial 2
		config.height = Nuggets.HEIGHT;   //
		config.title = Nuggets.TITLE;
		new LwjglApplication(new Nuggets(), config);
	}
}
