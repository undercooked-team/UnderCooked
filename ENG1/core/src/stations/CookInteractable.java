package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import cooks.GameEntity;

import helper.MapHelper;
import interactions.InputKey;

/** The CookInteractable Class. This is basically stations and pantries.*/
public abstract class CookInteractable extends GameEntity {
    /** The rectangle representing the collision of this interactable. */
    protected Rectangle interactRect;

    public CookInteractable(Rectangle rect) {
        super(rect);
        this.interactRect = rect;
    }

    public Rectangle getRectangle() {
        return this.interactRect;
    }

    public void interact(Cook cook, InputKey.InputTypes inputType) {

        //System.out.println("Ping! Interacted with station at x=" + body.getPosition().x*PPM + ", y=" + body.getPosition().x*PPM);

    }

}
