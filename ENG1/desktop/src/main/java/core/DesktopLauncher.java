package main.java.core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		// Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// config.setForegroundFPS(60);
		// config.setTitle("My GDX Game");
		// new Lwjgl3Application(new MyGdxGame(), config);
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setIdleFPS(60);
    config.setTitle("UnderCooked");
    config.setWindowedMode(960,640);
    new Lwjgl3Application(new Boot(), config);

  }
}
