package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import food.FoodItem;

public class Pantry extends CookInteractable {

    FoodItem.FoodID foodID;

    public Pantry(float width, float height, Body body, Rectangle rectangle) {
        super(width,height,body,rectangle);
    }

    public void setItem(FoodItem.FoodID foodID) {
        this.foodID = foodID;
    }

    public void interact(Cook cook) {
        System.out.println(foodID);
    }
}
