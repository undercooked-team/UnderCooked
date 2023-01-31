package helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import customers.Customer;
import food.FoodItem;
import food.FoodStack;
import food.Recipe;
import game.GameScreen;
import game.GameSprites;

// import java.awt.*;

/** Responsible for displaying information above the gameplay GameScreen. */
public class GameHud extends Hud {
    /** The label with the current amount of time played. */
    Label timeLabel;
    /** The label with the number of {@link Customer}s left to serve.  */
    Label CustomerLabel;
    Label CustomerScore;
    /** The {@link SpriteBatch} of the GameHud. Use for drawing {@link food.Recipe}s. */
    private SpriteBatch batch;
    /** The {@link FoodStack} that the {@link GameHud} should render. */
    private FoodStack recipe;
    /** The {@link Customer} to have their request rendered.. */
    private Customer customer;
    // /** The time, in milliseconds, of the last recipe change. */
    // private long lastChange;

    /**
     * The GameHud constructor.
     * @param batch The {@link SpriteBatch} to render
     * @param gameScreen The {@link GameScreen} to render the GameHud on
     */
    public GameHud(SpriteBatch batch, GameScreen gameScreen)
    {
        super(batch);

        timeLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        updateTime(0,0,0);

        CustomerLabel = new Label("CUSTOMERS LEFT:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(CustomerLabel).expandX().padTop(80).padRight(60);
        table.add(timeLabel).expandX().padTop(80).padLeft(60);

        this.batch = batch;
    }

    /**
     * Renders both the {@link Hud} with the game information and
     * the {@link Recipe} required the {@link customers.Customer} selected.
     * <br>The {@link Recipe} displays on the right side of the screen.
     */
    @Override
    public void render() {
        super.render();
        batch.begin();
        GameSprites gameSprites = GameSprites.getInstance();
        float drawX = Constants.RECIPE_X, drawY = Constants.RECIPE_Y;
        // If there is a recipe...
        if (recipe != null) {
            // Loop through on the top right of the screen, and render!
            for (FoodItem.FoodID ingredient : recipe.getStack()) {
                Sprite foodSprite = gameSprites.getSprite(GameSprites.SpriteID.FOOD, ingredient.toString());
                foodSprite.setScale(2F);
                foodSprite.setPosition(drawX-foodSprite.getWidth()/2,drawY-foodSprite.getHeight()/2);
                foodSprite.draw(batch);
                drawY -= 64;
            }
        }
        batch.end();
    }

    /* Removed as it was confusing to look at.
    /**
     * Changes the order of the {@link FoodItem}s in the recipe every second
     * to show which {@link FoodItem}s have non-specific places in the
     * {@link Recipe}.
     * /
    public void update() {
        if (recipe != null) {
            if (TimeUtils.timeSinceMillis(lastChange) > 1000) {
                this.recipe = Recipe.randomRecipeOption(recipeName);
                lastChange = TimeUtils.millis();
            }
        }
    }*/

    /**
     * Sets the recipe to be rendered.
     * @param customer The {@link Customer} who is requesting the {@link #recipe}.
     */
    public void setRecipe(Customer customer) {
        // this.lastChange = TimeUtils.millis();
        this.customer = customer;
        if (customer == null) {
            this.recipe = null;
            return;
        }
        this.recipe = Recipe.randomRecipeOption(customer.getRequestName());
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
        timeLabel.setText("TIMER: " + String.format(Util.formatTime(hoursPassed,minutesPassed,secondsPassed)));
    }

    /**
     * Set the Customer Count label
     * @param customerCount New Customer Count
     */
    public void setCustomerCount(int customerCount) {
        CustomerLabel.setText(String.format("CUSTOMERS: %d",customerCount));
    }

    /**
     * Getter for the {@link Customer} that has their
     * request being shown.
     * @return {@link Customer} : The {@link Customer} having their
     *                            request shown.
     */
    public Customer getCustomer() {
        return customer;
    }
}
