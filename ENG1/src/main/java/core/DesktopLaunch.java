package core;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLaunch {

    public static void main(String[] args)
    {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setIdleFPS(60);
        config.setTitle("UnderCooked");

        config.setWindowedMode(960,640);

        new Lwjgl3Application(new Boot(), config);
    }
}
