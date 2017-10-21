package de.voodoosoft.letrun.playground;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class LayoutLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Layout Sample";
		cfg.width = 1024;
		cfg.height = 768;
		new LwjglApplication(new LayoutSample(), cfg);
	}
}
