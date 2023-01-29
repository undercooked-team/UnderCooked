package stations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import cooks.GameEntity;

import interactions.InputKey;

import static helper.Constants.PPM;

public abstract class CookInteractable extends GameEntity {
    protected Rectangle interactRect;

    public CookInteractable(Body body, Rectangle rect) {
        super(rect.width, rect.height, body);
        this.interactRect = rect;
    }

    public Rectangle getRectangle() {
        return this.interactRect;
    }

    public void interact(Cook cook, InputKey.InputTypes inputType) {

        //System.out.println("Ping! Interacted with station at x=" + body.getPosition().x*PPM + ", y=" + body.getPosition().x*PPM);

    }

}
