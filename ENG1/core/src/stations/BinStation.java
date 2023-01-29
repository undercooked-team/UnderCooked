package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import interactions.InputKey;

public class BinStation extends Station {

    public BinStation(Body body, Rectangle rectangle) {
        super(body, rectangle);
    }

    @Override
    public void interact(Cook cook, InputKey.InputTypes inputType) {
        // Only bin if user inputs USE or PUT_DOWN
        if (inputType == InputKey.InputTypes.USE || inputType == InputKey.InputTypes.PUT_DOWN) {
            cook.foodStack.popStack();
        }
    }
}
