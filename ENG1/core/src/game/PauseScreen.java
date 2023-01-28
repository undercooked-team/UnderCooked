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
import helper.Constants;
import interactions.InputKey;
import interactions.Interactions;

public class PauseScreen extends ScreenAdapter {
    private ScreenController screenController;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Viewport viewport;
    private Stage stage;
    private GameScreen gameScreen;
    private ShapeRenderer shape;
    public PauseScreen(ScreenController screenController, OrthographicCamera orthographicCamera) {
        this.screenController = screenController;
        this.camera = orthographicCamera;

        this.batch = screenController.getSpriteBatch();
        this.gameScreen = ((GameScreen) screenController.getScreen(ScreenController.ScreenID.GAME));
        this.shape = screenController.getShapeRenderer();
        shape.setAutoShapeType(true);

        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, camera);
        stage = new Stage(viewport, batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label pauseLabel = new Label("PAUSED", font);
        table.add(pauseLabel).expandX();
        table.row();

        Label continueLabel = new Label("Press ESCAPE to continue", font);
        table.add(continueLabel).expandX();
        table.row();

        Label resetLabel = new Label("Press R to reset", font);
        table.add(resetLabel).expandX();
        table.row();

        pauseLabel.setFontScale(4);
        stage.addActor(table);
    }

    public void update(float delta) {
        Interactions.updateKeys();
        // Check if the Unpause key was pressed.
        if (Interactions.isJustPressed(InputKey.InputTypes.UNPAUSE)) {
            screenController.playGameScreen();
            return;
        }
        if (Interactions.isJustPressed(InputKey.InputTypes.RESET_GAME)) {
            screenController.resetGameScreen();
            screenController.setScreen(ScreenController.ScreenID.MENU);
        }
    }

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
