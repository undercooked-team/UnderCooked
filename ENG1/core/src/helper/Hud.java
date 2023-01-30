package helper;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** The Hud Class. {@link GameHud} inherits this class. */
public class Hud {

    /** Responsible for holding the table to render. */
    protected Stage stage;
    /** The viewport of the Hud. */
    protected Viewport viewport;
    /** Sizes and positions children using position constraints. */
    protected Table table;

    /**
     * Hud Constructor.
     * @param batch The SpriteBatch to render on this Hud.
     */
    public Hud(SpriteBatch batch) {
        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        table = new Table();
        table.top();
        table.setFillParent(true);

        stage.addActor(table);
    }

    /** Render this Hud.*/
    public void render() {
        stage.draw();
    }
}
