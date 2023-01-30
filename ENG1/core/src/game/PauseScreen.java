package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.ScreenController.ScreenID;
import helper.Constants;
import interactions.InputKey;
import interactions.Interactions;

/**
 * A {@link ScreenAdapter} that is used when the game is paused.
 * It renders the {@link GameScreen} behind it, so the user can still
 * see the game.
 */
public class PauseScreen extends ScreenAdapter {
    private ScreenController screenController;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Viewport viewport;
    private Stage stage;
    private GameScreen gameScreen;
    private ShapeRenderer shape;

    /**
     * The constructor for the {@link PauseScreen}.
     * @param screenController The {@link ScreenController} of the {@link ScreenAdapter}.
     * @param orthographicCamera The {@link OrthographicCamera} that the game should use.
     */
    public PauseScreen(ScreenController screenController, OrthographicCamera orthographicCamera) {
        this.screenController = screenController;
        this.camera = orthographicCamera;

        this.batch = screenController.getSpriteBatch();
        this.gameScreen = ((GameScreen) screenController.getScreen(ScreenID.GAME));
        this.shape = screenController.getShapeRenderer();
        shape.setAutoShapeType(true);

        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, camera);
        stage = new Stage(viewport, batch);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        String[] strLabels = new String[] {
            "PAUSED",
            String.format("Press %s to continue",Interactions.getKeyString(InputKey.InputTypes.UNPAUSE)),
            String.format("Press %s for instructions",Interactions.getKeyString(InputKey.InputTypes.INSTRUCTIONS)),
            String.format("Press %s for credits",Interactions.getKeyString(InputKey.InputTypes.CREDITS)),
            String.format("Press %s to reset",Interactions.getKeyString(InputKey.InputTypes.RESET_GAME)),
            String.format("Press %s to quit",Interactions.getKeyString(InputKey.InputTypes.QUIT))
        };
        /* OLD CODE
        Label pauseLabel = new Label("PAUSED", font);
        table.add(pauseLabel).expandX();
        table.row();

        Label continueLabel = new Label(String.format("Press %s to continue",Interactions.getKeyString(InputKey.InputTypes.UNPAUSE)), font);
        table.add(continueLabel).expandX();
        table.row();

        Label instructionsLabel = new Label(String.format("Press %s for instructions",Interactions.getKeyString(InputKey.InputTypes.INSTRUCTIONS)), font);
        table.add(instructionsLabel).expandX();
        table.row();

        Label resetLabel = new Label(String.format("Press %s to reset",Interactions.getKeyString(InputKey.InputTypes.RESET_GAME)), font);
        table.add(resetLabel).expandX();
        table.row();
        */

        /** Contains the Labels objects for the PauseScreen */
        Label[] lblLabels = new Label[strLabels.length];

        for (int j = 0; j < lblLabels.length; j++) {
            String strLabel = strLabels[j];
            lblLabels[j] = new Label(String.format(strLabel), font);
            table.add(lblLabels[j]).expandX();
            table.row();
        }

        // pauseLabel.setFontScale(4);
        lblLabels[0].setFontScale(4);
        stage.addActor(table);
    }

    /**
     * Check for user input every frame and act on specified inputs.
     * @param delta The time between frames as a float.
     */
    public void update(float delta) {
        Interactions.updateKeys();
        // Check if the Unpause key was pressed.
        if (Interactions.isJustPressed(InputKey.InputTypes.UNPAUSE)) {
            screenController.playGameScreen();
            return;
        }
        if (Interactions.isJustPressed(InputKey.InputTypes.INSTRUCTIONS)) {
            ((InstructionScreen)screenController.getScreen(ScreenID.INSTRUCTIONS)).setPrevScreenID(ScreenID.PAUSE);
            screenController.setScreen(ScreenID.INSTRUCTIONS);
        }
        else if (Interactions.isJustPressed(InputKey.InputTypes.CREDITS)) {
            ((CreditsScreen)screenController.getScreen(ScreenID.CREDITS)).setPrevScreenID(ScreenID.PAUSE);
            screenController.setScreen(ScreenID.CREDITS);
        }
        else if (Interactions.isJustPressed(InputKey.InputTypes.RESET_GAME)) {
            screenController.resetGameScreen();
            screenController.setScreen(ScreenID.MENU);
        }
        else if (Interactions.isJustPressed(InputKey.InputTypes.QUIT)) {
            Gdx.app.exit();
        }
    }

    /**
     * The function used to render the {@link PauseScreen}.
     *
     * <br>Draws the {@link GameScreen} underneath using the
     * {@link GameScreen#renderGame(float)} function, and then
     * renders the {@link PauseScreen} over it.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        gameScreen.renderGame(delta);

        shape.begin(ShapeRenderer.ShapeType.Filled);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shape.setColor(0,0,0,0.5F);
        shape.rect(0,0, Constants.V_Width,Constants.V_Height);
        shape.setColor(Color.WHITE);
        shape.end();

        stage.draw();

        Gdx.gl.glDisable(GL20.GL_BLEND);

        this.update(delta);

    }
}
