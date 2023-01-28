package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;

public class BinStation extends Station {

    public BinStation(float width, float height, Body body, Rectangle rectangle) {
        super(width, height, body, rectangle);
    }

    @Override
    public void interact(Cook cook) {
        cook.foodStack.popStack();
    }
}
