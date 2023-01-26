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

// import java.awt.*;

public class Hud {


    public Stage stage;
    private Viewport viewport;
    private int timeCount=0, lastTime = 0, WorldTimer=0 ;
    private int CustomerCount;



    Label timeLabel;
    Label CustomerLabel;
    Label timer;
    Label CustomerScore;


    public Hud(SpriteBatch batch)
    {

        timeCount = 0;
        CustomerCount = 5;
        viewport = new FitViewport(Constants.V_Width, Constants.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        timeLabel = new Label(String.format("%03d", WorldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timer = new Label("TIMER:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        CustomerLabel = new Label("CUSTOMERS:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        CustomerScore = new Label(String.format("%01d", CustomerCount), new Label.LabelStyle(new BitmapFont(), Color.BLACK));



        table.add(CustomerLabel).expandX().padTop(60);
        table.add(CustomerScore).expandX().padTop(60);


        table.add(timer).expandX().padTop(60);
        table.add(timeLabel).expandX().padTop(60);

        stage.addActor(table);

    }

    public void update(int secondsPassed)
    {

        timeLabel.setText(String.format("%03d", secondsPassed));


    }
}
