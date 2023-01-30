package game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.HashMap;

/**
 * A class to control the different {@link ScreenAdapter} that the game
 * switches between.
 */
public class ScreenController {

    private Boot boot;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private GameOverScreen gameOverScreen;
    private CreditsScreen creditsScreen;
    private InstructionScreen instructionScreen;
    private PauseScreen pauseScreen;
    private HashMap<ScreenID, ScreenAdapter> screens;
    private long playTimeDiff;
    private long customerTimeDiff;

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
        this.creditsScreen = new CreditsScreen(this,camera);

        this.screens = new HashMap<>();
        this.screens.put(ScreenID.MENU,menuScreen);
        this.screens.put(ScreenID.GAME,gameScreen);
        this.screens.put(ScreenID.GAMEOVER, gameOverScreen);
        this.screens.put(ScreenID.INSTRUCTIONS,instructionScreen);
        this.screens.put(ScreenID.CREDITS,creditsScreen);

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

    /**
     * An intermediate function to get the SpriteBatch from the {@link Boot}.
     * @return {@link SpriteBatch} : {@link SpriteBatch} for the game.
     */
    public SpriteBatch getSpriteBatch() { return boot.getSpriteBatch(); }
    /**
     * An intermediate function to get the {@link ShapeRenderer} from the {@link Boot}.
     * @return {@link ShapeRenderer} : {@link ShapeRenderer} for the game.
     */
    public ShapeRenderer getShapeRenderer() { return boot.getShapeRenderer(); }

    /** The different states that the game can be in.*/
    public enum ScreenID {
        /** The {@link MenuScreen}, where the program opens to. */
        MENU,
        /** The {@link GameScreen}, where the game is played. */
        GAME,
        /** The {@link PauseScreen}, where the game is paused, and the player can
         *  rest, look at instructions and credits, reset or quit. */
        PAUSE,
        /** The {@link GameOverScreen}, which is opened once the game has finished. */
        GAMEOVER,
        /** The {@link InstructionScreen}, where the instructions for the game are displayed. */
        INSTRUCTIONS,
        /** The {@link CreditsScreen}, where the game shows credit for the assets we used
         *  within the game. */
        CREDITS
    }

    /**
     * Get the desired screen from the {@link ScreenController}.
     * @param screenID The {@link ScreenID} of the screen you want.
     * @return {@link ScreenAdapter} : The requested {@link ScreenAdapter}.
     */
    public ScreenAdapter getScreen(ScreenID screenID) {
        return this.screens.get(screenID);
    }

    /** Reset the game to the initial state. */
    public void resetGameScreen() {
        gameScreen.reset();
        instructionScreen.setPrevScreenID(ScreenID.MENU);
    }

    /** Pause the game. */
    public void pauseGameScreen() {
        playTimeDiff = TimeUtils.millis() - gameScreen.getPreviousSecond();
        customerTimeDiff = gameScreen.getNextCustomerSecond() - TimeUtils.millis();
        setScreen(ScreenID.PAUSE);
    }

    /** Resume the game from pause.
     * Only call this AFTER {@link #pauseGameScreen()}. */
    public void playGameScreen() {
        gameScreen.setPreviousSecond(TimeUtils.millis()- playTimeDiff);
        gameScreen.setNextCustomerSecond(TimeUtils.millis() - customerTimeDiff);
        setScreen(ScreenID.GAME);
    }

}
