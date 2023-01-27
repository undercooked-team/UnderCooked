package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import food.FoodItem;

public class PreperationStation extends Station {
    public PreperationStation(float width, float height, Body body, Rectangle rectangle) {
        super(width, height, body, rectangle);
    }

    @Override
    public void interact(Cook cook) {
        // If Cook is not holding any food, stop here.
        if (cook.foodStack.peekStack() == null) {
            System.out.println("No FoodItem held.");
            return;
        }
        // Add the new proccessed item onto the stack.
        FoodItem.FoodID newFood = interactions.Interactions.interaction(cook.foodStack.peekStack(), stationID);
        if (newFood != null) {
            cook.foodStack.popStack();
            cook.foodStack.addStack(newFood);
        }
        else {
            // Code to run when top FoodItem cannot interact with the station.
        }
    }
}
