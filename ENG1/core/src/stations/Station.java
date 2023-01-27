package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import food.FoodItem.FoodID;
import interactions.Interactions;

public class Station extends CookInteractable {

    /** IDs of all the different possible types of stations.*/
    public enum StationID {
        fry,
        cut,
        counter,
        bin,
        none
    }

    StationID stationID;

    public Station(float width, float height, Body body, Rectangle rectangle) {
        super(width,height,body,rectangle);
    }

    public void setID(StationID stationID) {
        this.stationID = stationID;
    }

    public void interact(Cook cook) {
        System.out.println(stationID);
        // Add the new proccessed item onto the stack.
        FoodID newFood = interactions.Interactions.interaction(cook.foodStack.peekStack(), stationID);
        if (newFood != null) {
            cook.foodStack.popStack();
            cook.foodStack.addStack(newFood);
        }
        else {
            // Code to run when top FoodItem cannot interact with the station.
        }
        System.out.println(cook.foodStack);
    }
}
