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
import game.GameScreen;

// import java.awt.*;

public class GameHud extends Hud {


    public Stage stage;
    private Viewport viewport;
    private int WorldTimer=0;

    Label timeLabel;
    Label CustomerLabel;
    Label timer;
    Label CustomerScore;


    public GameHud(SpriteBatch batch, GameScreen gameScreen)
    {
        super(batch);


        timeLabel = new Label(String.format("%03d", WorldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timer = new Label("TIMER:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        CustomerLabel = new Label("CUSTOMERS:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        CustomerScore = new Label(String.format("%01d", gameScreen.getCustomerCount()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));


        table.add(CustomerLabel).expandX().padTop(60);
        table.add(CustomerScore).expandX().padTop(60);


        table.add(timer).expandX().padTop(60);
        table.add(timeLabel).expandX().padTop(60);

    }

    public void updateTime(int secondsPassed) {
        updateTime(0,0,secondsPassed);
    }

    public void updateTime(int minutesPassed, int secondsPassed) {
        updateTime(0,minutesPassed,secondsPassed);
    }

    public void updateTime(int hoursPassed, int minutesPassed, int secondsPassed)
    {
        timeLabel.setText(String.format(Util.formatTime(hoursPassed,minutesPassed,secondsPassed)));
    }

    public void setCustomerCount(int customerCount) {
        CustomerLabel.setText(String.format("CUSTOMERS: %d",customerCount));
    }
}
