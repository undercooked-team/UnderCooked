package helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import game.GameScreen;

// import java.awt.*;

/** Responsible for displaying information above the gameplay GameScreen. */
public class GameHud extends Hud {

    // public Stage stage;
    // private Viewport viewport;

    /** The timer being used in the game, and being displayed by the {@link GameHud}. */
    // NOTE: World Timer seems a little redundant
    private int WorldTimer=0;

    /** The label saying 'timer:' . */
    Label timeLabel;
    /** The label saying 'customers:'  */
    Label CustomerLabel;
    /** The label {@link worldTimer} outputs to. */
    Label timer;
    /** The label {@link gameScreen.getCustomerCount()} outputs to. */
    Label CustomerScore;

    /**
     * The GameHud constructor.
     * @param batch The {@link SpriteBatch} to render
     * @param gameScreen The {@link GameScreen} to render the GameHud on
     */
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

    /**
     * Update the Timer
     * @param secondsPassed The number of seconds passed
     */
    public void updateTime(int secondsPassed) {
        updateTime(0,0,secondsPassed);
    }

    /**
     * Update the Timer
     * @param minutesPassed The number of minutes passed
     * @param secondsPassed The number of seconds passed
     */
    public void updateTime(int minutesPassed, int secondsPassed) {
        updateTime(0,minutesPassed,secondsPassed);
    }

    /**
     * Update the Timer
     * @param hoursPassed The number of hours passed
     * @param minutesPassed The number of minutes passed
     * @param secondsPassed The number of seconds passed
     */
    public void updateTime(int hoursPassed, int minutesPassed, int secondsPassed)
    {
        timeLabel.setText(String.format(Util.formatTime(hoursPassed,minutesPassed,secondsPassed)));
    }

    /**
     * Set the Customer Count label
     * @param customerCount New Customer Count
     */
    public void setCustomerCount(int customerCount) {
        CustomerLabel.setText(String.format("CUSTOMERS: %d",customerCount));
    }
    /**
     * WorldTimer getter
     * @return WorldTimer
     */
    public float GetTime(){return WorldTimer;}
}
