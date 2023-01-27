package game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends ScreenAdapter {

    private ScreenController screenController;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    public MenuScreen(ScreenController screenController, OrthographicCamera orthographicCamera) {
        this.screenController = screenController;
        this.camera = orthographicCamera;
        this.batch = screenController.getSpriteBatch();
    }

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            screenController.setScreen(ScreenController.ScreenID.GAME);
        }
    }

    @Override
    public void render(float delta) {
        this.update();
    }
}
