package stations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import cooks.GameEntity;

import food.FoodItem.FoodID;
import java.util.HashMap;
import other.InteractionKey;

import static helper.Constants.PPM;

public class CookInteractable extends GameEntity {
    protected Rectangle interactRect;

    public CookInteractable(float width, float height, Body body, Rectangle rect) {
        super(width, height, body);
        this.interactRect = rect;
    }

    public Rectangle getRectangle() {
        return this.interactRect;
    }

    public void interact(Cook cook) {

        System.out.println("Ping! Interacted with station at x=" + body.getPosition().x*PPM +
                ", y=" + body.getPosition().x*PPM);

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
