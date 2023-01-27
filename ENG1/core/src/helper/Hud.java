package helper;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {

    protected Stage stage;
    protected Viewport viewport;
    protected Table table;

    public Hud(SpriteBatch batch) {
        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        table = new Table();
        table.top();
        table.setFillParent(true);

        stage.addActor(table);
    }

    public void render() {
        stage.draw();
    }
}
