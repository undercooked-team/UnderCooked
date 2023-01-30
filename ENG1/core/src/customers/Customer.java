package customers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import game.GameScreen;
import helper.Constants;

public class Customer {

    public Vector2 position;
    public Sprite sprite;
    private GameScreen screen;

    private boolean canDraw = false;

    public Customer(Sprite sprite, GameScreen gameScreen)
    {

        this.sprite = sprite;
        position = Constants.customerSpawn;
        sprite.setScale(Constants.customerScale);
        this.screen = gameScreen;
    }

    public void Draw(SpriteBatch batch)
    {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public boolean canSpawn()
    {
        return canDraw;
    }

    public void setCanDraw(boolean value)
    {
        this.canDraw = value;

    }

    public void updateCustomerCount()
    {
        screen.updateCustomers();
    }

    public void addNewCustomer()
    {
        screen.addnewCustomer();
    }



}
