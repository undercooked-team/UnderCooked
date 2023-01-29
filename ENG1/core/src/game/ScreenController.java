package game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.HashMap;

public class ScreenController {

    private Boot boot;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private GameOverScreen gameOverScreen;
    private InstructionScreen instructionScreen;
    private PauseScreen pauseScreen;
    private HashMap<ScreenID, ScreenAdapter> screens;
    private long timeDiff;

    /**
     * Screen Controller Constructor
     * @param boot The class responsible for initializing the first game elements
     * @param camera A camera instance (The one that the boot class creates is fine)
     */
    public ScreenController(Boot boot, OrthographicCamera camera) {
        this.boot = boot;
        this.gameScreen = new GameScreen(this,camera);
        this.menuScreen = new MenuScreen(this,camera);
        this.gameOverScreen = new GameOverScreen(this,camera);
        this.instructionScreen = new InstructionScreen(this,camera);

        this.screens = new HashMap<>();
        this.screens.put(ScreenID.MENU,menuScreen);
        this.screens.put(ScreenID.GAME,gameScreen);
        this.screens.put(ScreenID.GAMEOVER, gameOverScreen);
        this.screens.put(ScreenID.INSTRUCTIONS,instructionScreen);

        this.pauseScreen = new PauseScreen(this,camera);
        this.screens.put(ScreenID.PAUSE,pauseScreen);
    }

    /**
     * Change the screen of the game to screenID
     * @param screenID The ID of the new screen you want
     */
    public void setScreen(ScreenID screenID) {
        this.boot.setScreen(this.screens.get(screenID));
    }

    public SpriteBatch getSpriteBatch() { return boot.getSpriteBatch(); }
    public ShapeRenderer getShapeRenderer() { return boot.getShapeRenderer(); }

    /** The different states that the game can be in.*/
    public enum ScreenID {
        MENU,
        GAME,
        PAUSE,
        GAMEOVER,
        INSTRUCTIONS
    }

    public ScreenAdapter getScreen(ScreenID screenID) {
        return this.screens.get(screenID);
    }

    public void resetGameScreen() {
        gameScreen.reset();
        instructionScreen.setPrevScreenID(ScreenID.MENU);
    }

    public void pauseGameScreen() {
        timeDiff = TimeUtils.millis() - gameScreen.getPreviousSecond();
        setScreen(ScreenID.PAUSE);
    }

    public void playGameScreen() {
        gameScreen.setPreviousSecond(TimeUtils.millis()-timeDiff);
        setScreen(ScreenID.GAME);
    }

}
