package Customers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import helper.Constants;
import helper.MapHelper;

public class Customer {

    public Vector2 position;
    public Sprite sprite;

    private boolean canDraw= false;

    public Customer(Texture img)
    {

        this.sprite = new Sprite(img);

        position = Constants.customerSpawn;
        sprite.setScale(Constants.customerScale);

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



}
