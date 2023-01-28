package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.utils.viewport.Viewport;
import helper.Constants;
import helper.Util;
import interactions.InputKey;
import interactions.Interactions;

public class GameOverScreen extends ScreenAdapter {
    private Viewport viewport;
    private ScreenController screenController;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;

    private Label timeLabel;


    public GameOverScreen(ScreenController screenController, OrthographicCamera orthographicCamera) {

        this.screenController = screenController;
        this.camera = orthographicCamera;
        this.batch = screenController.getSpriteBatch();

        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, camera);
        stage = new Stage(viewport, batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        gameOverLabel.setFontScale(3);
        table.add(gameOverLabel).expandX();

        table.row();

        timeLabel = new Label("0:00", font);
        timeLabel.setFontScale(2);
        table.add(timeLabel);

        table.row();

        Label extraText = new Label(String.format("To restart, press %s.",Interactions.getKeyString(InputKey.InputTypes.RESET_GAME)), font);
        extraText.setFontScale(1);
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
        if (Interactions.isJustPressed(InputKey.InputTypes.RESET_GAME)) {
            screenController.resetGameScreen();
            screenController.setScreen(ScreenController.ScreenID.MENU);
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void setTime(int hours, int minutes, int seconds) {
        timeLabel.setText(Util.formatTime(hours,minutes,seconds));
    }
}
