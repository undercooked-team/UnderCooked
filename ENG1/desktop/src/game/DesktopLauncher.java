package game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import helper.Constants;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
/** The launcher for the desktop version of the game. */
public class DesktopLauncher {
    /**
     * The function that is called when the executable jar file
     * is opened that starts running the game using {@link Boot}.
     * @param arg Arguments for running the game's jar file.
     */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("UnderCooked");
		config.setWindowedMode(Constants.V_Width,Constants.V_Height);
		new Lwjgl3Application(Boot.getInstance(), config);
	}
}
