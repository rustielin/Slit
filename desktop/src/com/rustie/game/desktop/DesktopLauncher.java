package com.rustie.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rustie.game.Slit;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Slit.WIDTH;
		config.height = Slit.HEIGHT;
		config.title = Slit.TITLE;
		new LwjglApplication(new Slit(), config);
	}
}
