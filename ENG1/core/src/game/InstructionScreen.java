package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import helper.Constants;
import interactions.InputKey;
import interactions.Interactions;

public class InstructionScreen extends ScreenAdapter {

    private OrthographicCamera camera;
    private ScreenController screenController;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    public InstructionScreen(ScreenController screenController, OrthographicCamera orthographicCamera) {
        this.screenController = screenController;
        this.camera = orthographicCamera;
        this.batch = screenController.getSpriteBatch();

        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, camera);
        stage = new Stage(viewport, batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("Instructions", font);
        gameOverLabel.setFontScale(3);
        table.add(gameOverLabel).expandX();

        table.row();

        table.row();

        Label extraText = new Label("To exit, press I", font);
        extraText.setFontScale(1.5F);
        table.add(extraText);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Check for input.
        Interactions.updateKeys();
        if (Interactions.isJustPressed(InputKey.InputTypes.INSTRUCTIONS)) {
            screenController.setScreen(ScreenController.ScreenID.MENU);
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
}
