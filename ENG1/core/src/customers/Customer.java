package customers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import cooks.GameEntity;
import food.FoodStack;
import food.Recipe;
import game.GameScreen;
import helper.Constants;

public class Customer {

    public Vector2 position;
    public Sprite sprite;
    private String request;

    private boolean canDraw = false;

    public Customer(Sprite sprite)
    {
        this.sprite = sprite;
        this.position = Constants.customerSpawn;
        this.request = Recipe.randomRecipe();
    }

    public Customer(Sprite sprite, Vector2 position) {
        this(sprite);
        this.position = position;
    }

    public void setCanDraw(boolean value)
    {
        this.canDraw = value;

    }

    public void randomRecipe() {

    }

    public void render(SpriteBatch batch) {
        sprite.setPosition(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
        sprite.draw(batch);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    /**
     * Getter to get the name of the request of the {@link Customer}.
     * @return {@link String} : The name of the {@link Customer}'s
     *                          {@link Recipe} request.
     */
    public String getRequestName() {
        return request;
    }
}
