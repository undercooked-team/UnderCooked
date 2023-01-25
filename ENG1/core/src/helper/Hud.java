package helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.awt.*;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private float timeCount;



    Label timeLabel;
    Label CustomerLabel;


    public Hud(SpriteBatch batch)
    {

        timeCount = 0;

        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        timeLabel = new Label("TIME:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        CustomerLabel = new Label("CUSTOMERS:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));



        table.add(CustomerLabel).expandX().padTop(20);

        table.add(timeLabel).expandX().padTop(20);

        stage.addActor(table);

    }
}
