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

import game.ScreenController.ScreenID;
import helper.Constants;
import interactions.InputKey;
import interactions.Interactions;

public class CreditsScreen extends ScreenAdapter {

    private ScreenID prevScreenID = ScreenID.MENU;
    private OrthographicCamera camera;
    private ScreenController screenController;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    public CreditsScreen(ScreenController screenController, OrthographicCamera orthographicCamera) {
        this.screenController = screenController;
        this.camera = orthographicCamera;
        this.batch = screenController.getSpriteBatch();

        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, camera);
        stage = new Stage(viewport, batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label creditsLabel = new Label("Credits", font);
        creditsLabel.setFontScale(3);
        table.add(creditsLabel).expandX();

        table.row();

        String[] instructions = new String[] {
            "Credits for the sprites: (We generally edited them a little)",
            "Creator: poloviiinkin",
            "Link to food_graphics: https://poloviiinkin.itch.io/food",
            "Link to other_sprites: https://poloviiinkin.itch.io/mega-pixel-art-32x32-px-icons-sprite-sheet",
            "Link to license: https://creativecommons.org/licenses/by-sa/4.0/deed.en",
            ""
        };

        for (String instruction : instructions) {
            Label instLabel = new Label(instruction, font);
            table.add(instLabel).expandX();
            table.row();
        }

        Label extraText = new Label(String.format("To go back, press %s", Interactions.getKeyString(InputKey.InputTypes.CREDITS)), font);
        extraText.setFontScale(1.5F);
        table.add(extraText);

        stage.addActor(table);
    }

    // @Override
    // public void show() {
    // }

    @Override
    public void render(float delta) {
        // Check for input.
        Interactions.updateKeys();
        if (Interactions.isJustPressed(InputKey.InputTypes.CREDITS)) {
            screenController.setScreen(prevScreenID);
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    public void setPrevScreenID(ScreenID scID) {
        prevScreenID = scID;
    }
}

