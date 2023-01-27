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

public class GameHud extends Hud {


    public Stage stage;
    private Viewport viewport;
    private int timeCount=0, lastTime = 0, WorldTimer=0 ;
    private int CustomerCount;



    Label timeLabel;
    Label CustomerLabel;
    Label timer;
    Label CustomerScore;


    public GameHud(SpriteBatch batch)
    {
        super(batch);
        timeCount = 0;
        CustomerCount = 5;


        timeLabel = new Label(String.format("%03d", WorldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timer = new Label("TIMER:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        CustomerLabel = new Label("CUSTOMERS:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        CustomerScore = new Label(String.format("%01d", CustomerCount), new Label.LabelStyle(new BitmapFont(), Color.BLACK));


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
        String timeString = "";
        if (hoursPassed > 0) {
            timeString += hoursPassed + ":";
        }
        timeString += minutesPassed + ":";
        if (secondsPassed < 10) {
            timeString += "0";
        }
        timeString += secondsPassed;
        timeLabel.setText(String.format(timeString));
    }
    public void UpdateCustomers()
    {
        if(CustomerCount<1)
        {
            throw new RuntimeException("Customer count should not go below 0.");
        }
        CustomerCount--;
        CustomerScore.setText(String.format("%1d",CustomerCount));
    }
    public int GetCustomers()
    {
        return CustomerCount;
    }
}
