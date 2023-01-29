package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class InstructionScreen extends ScreenAdapter {

    private ScreenID prevScreenID = ScreenID.MENU;
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

        String[] instructions = new String[] {
                String.format("Move using the WASD keys, or the arrow keys. Swap the cook you are controlling by pressing %s",Interactions.getKeyString(InputKey.InputTypes.COOK_SWAP)),
                "",
                String.format("To use a station, press %s while facing it.", Interactions.getKeyString(InputKey.InputTypes.USE)),
                String.format("Take items from the Pantries (tables with ingredients on them) or a Station by pressing %s.", Interactions.getKeyString(InputKey.InputTypes.PICK_UP)),
                "Pantries have an infinite number of resources, and the Cook has no carry limit.",
                "",
                String.format("You can put an item down on a table by pressing %s.", Interactions.getKeyString(InputKey.InputTypes.PUT_DOWN)),
                "Counters can do this for any item, but preparation stations require valid ingredients.",
                "",
                String.format("You progress ingredient preparation by using (%s) the station when the bar is yellow.", Interactions.getKeyString(InputKey.InputTypes.USE)),
                // // I feel the below are unnecessary and the player can figure this out themselves.
                // "",
                // "Buns are added to the stack by giving you the opposite of the highest bun,",
                // "This means if your highest bun is a bottom bun, then you'll get a top bun.",
                // "",
                String.format("The bin allows you to dispose of items you no longer need. (%s or %s)",
                        Interactions.getKeyString(InputKey.InputTypes.USE),
                        Interactions.getKeyString(InputKey.InputTypes.PUT_DOWN)),
                "",
                "Each customer has a range of different foods they can request from.",
                String.format("The recipe of their request will be display if you interact with the station in any way. (%s, %s or %s)",
                        Interactions.getKeyString(InputKey.InputTypes.USE),
                        Interactions.getKeyString(InputKey.InputTypes.PICK_UP),
                        Interactions.getKeyString(InputKey.InputTypes.PUT_DOWN)),
                "",
                "Your goal is to successfully give every customer the food they request, and the game will end once you do.",
                "",
                String.format("You can pause the game by pressing %s.", Interactions.getKeyString(InputKey.InputTypes.PAUSE)),
                ""
        };

        for (String instruction : instructions) {
            Label instLabel = new Label(instruction, font);
            table.add(instLabel).expandX();
            table.row();
        }

        Label extraText = new Label("To go back, press I", font);
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
