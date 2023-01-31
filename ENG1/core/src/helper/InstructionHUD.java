package helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import game.GameScreen;

import java.awt.*;

public class InstructionHUD extends Hud{
    /**
     * Hud Constructor.
     *
     * @param batch The SpriteBatch to render on this Hud.
     */

    Label InstructionsLabel;
    private SpriteBatch batch;

    public InstructionHUD(SpriteBatch batch, GameScreen gameScreen) {
        super(batch);
        InstructionsLabel = new Label("Press I to USE \nPress O to COLLECT \nPress J to PUT DOWN", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(InstructionsLabel).expandX().padTop(110).padRight(480);

        this.batch = batch;
    }

    @Override
    public void render() {

        super.render();


    }


}
