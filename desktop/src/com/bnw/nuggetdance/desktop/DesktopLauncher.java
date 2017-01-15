package com.bnw.nuggetdance.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bnw.nuggetdance.Constants.ApplicationConstants;
import com.bnw.nuggetdance.Nuggets;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 480;     //added in tutorial 2
		config.height = 800;   //
		config.title = ApplicationConstants.TITLE;

		new LwjglApplication(new Nuggets(), config);
	}
}
