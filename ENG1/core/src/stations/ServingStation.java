package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import interactions.InputKey;

public class ServingStation extends CounterStation {
    public ServingStation(float width, float height, Body body, Rectangle rectangle) {
        super(width, height, body, rectangle);
    }

    @Override
    public void interact(Cook cook, InputKey.InputTypes inputType) {
        // Only call super if the input is "USE"
        if (inputType == InputKey.InputTypes.USE) {
            super.interact(cook, inputType);
        }
    }
}
