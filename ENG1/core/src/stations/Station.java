package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;

public class Station extends CookInteractable {

    public Station(float width, float height, Body body, Rectangle rectangle) {
        super(64,64,body,rectangle);
    }

    public void interact(Cook cook) {

    }
}
