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

/**
 * A {@link CreditsScreen} to give credit
 * to the source of the assets used.
 */
public class CreditsScreen extends ScreenAdapter {

    private ScreenID prevScreenID = ScreenID.MENU;
    private OrthographicCamera camera;
    private ScreenController screenController;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;

    /**
     * The constructor for the {@link CreditsScreen}.]
     * @param screenController The {@link ScreenController} of the {@link ScreenAdapter}.
     * @param orthographicCamera The {@link OrthographicCamera} that the game should use.
     */
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
            "",
            "Cook forward facing: http://pixelartmaker.com/art/77e4b71a43c348d",
            "",
            "Program to create the texture atlases: https://github.com/crashinvaders/gdx-texture-packer-gui/releases",
            "Program to create the map: https://www.mapeditor.org/"
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

    /**
     * Check for user input every frame and act on specified inputs.
     * @param delta The time between frames as a float.
     */
    public void update(float delta) {
        // Check for input.
        Interactions.updateKeys();
        if (Interactions.isJustPressed(InputKey.InputTypes.CREDITS)) {
            screenController.setScreen(prevScreenID);
        }
    }

    /**
     * The function used to render the {@link CreditsScreen}.
     *
     * <br>Draws the {@link #stage} of the {@link CreditsScreen},
     * which contains all the text as {@link Label}s.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        this.update(delta);
    }

    /**
     * Sets the variable {@link #prevScreenID} to the input,
     * which allows the {@link CreditsScreen} to return the
     * player to the screen they opened it from.
     * @param scID The {@link ScreenController.ScreenID} of the previous {@link ScreenAdapter}.
     */
    public void setPrevScreenID(ScreenID scID) {
        prevScreenID = scID;
    }
}

