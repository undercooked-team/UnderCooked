package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import food.FoodItem;
import stations.Station;

import java.util.HashMap;

public class ScreenController {

    private Boot boot;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private HashMap<ScreenID, ScreenAdapter> screens;
    // private PauseScreen pauseScreen;

    public ScreenController(Boot boot, OrthographicCamera camera) {
        this.boot = boot;
        this.gameScreen = new GameScreen(this,camera);
        this.menuScreen = new MenuScreen(this,camera);

        this.screens = new HashMap<>();
        this.screens.put(ScreenID.MENU,menuScreen);
        this.screens.put(ScreenID.GAME,gameScreen);
    }

    public void setScreen(ScreenID screenID) {
        this.boot.setScreen(this.screens.get(screenID));
    }

    public SpriteBatch getSpriteBatch() {
        return boot.getSpriteBatch();
    }

    enum ScreenID {
        MENU,
        GAME,
        PAUSE
    }

    public ScreenAdapter getScreen(ScreenID screenID) {
        return this.screens.get(screenID);
    }

}
