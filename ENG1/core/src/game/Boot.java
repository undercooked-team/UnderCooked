package game;

// import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import helper.Constants;

/** Responsible for starting the game.
 * The singleton used to initialize all game elements.*/
public class Boot extends Game {

    /** The Boot Singleton Instance. */
    public static Boot INSTANCE;
    /** The screen width and height. */
    // private int widthScreen, heightScreen;
    /** The camera for the game. */
    private OrthographicCamera orthographicCamera;
    /** Boot Singleton Constructor */
    private Boot()
    {
        INSTANCE = this;
    }
    /**
     * Returns the Singleton Boot instance
     * @return The Boot INSTANCE
     */
    public static Boot getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Boot();
        }
        return INSTANCE;
    }

    /** The Viewport for the game. */
    private Viewport gamePort;
    /** The spriteBatch for the game. */
    private SpriteBatch spriteBatch;
    /** The shapeRenderer for the game. */
    private ShapeRenderer shapeRenderer;
    /** The screenController used by the game. */
    private ScreenController screenController;

    @Override
    public void create()
    {
        // this.widthScreen = Gdx.graphics.getWidth();
        // this.heightScreen = Gdx.graphics.getHeight();
        this.orthographicCamera = new OrthographicCamera();
        this.spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
        gamePort = new FitViewport(Constants.V_Width, Constants.V_Height, orthographicCamera);
        this.screenController = new ScreenController(this, orthographicCamera);
        setScreen(new MenuScreen(screenController, orthographicCamera));
    }

    public void resize(int width, int height)
    {
        gamePort.update(width, height);
    }

    /**
     * The {@link SpriteBatch} getter
     * @return {@link SpriteBatch} : The {@link SpriteBatch} for the game.
     */
    public SpriteBatch getSpriteBatch() { return spriteBatch; }
    /**
     * The {@link ShapeRenderer} getter.
     * @return {@link ShapeRenderer} : The {@link ShapeRenderer} for the game.
     */
    public ShapeRenderer getShapeRenderer() { return shapeRenderer; }

    /**
     * The {@link ScreenController} getter.
     * @return {@link ScreenController} : The {@link ScreenController} for the game.
     */
    public ScreenController getScreenController() { return screenController; }

}
