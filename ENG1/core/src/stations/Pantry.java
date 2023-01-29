package stations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import cooks.Cook;
import food.FoodItem;
import interactions.InputKey;

public class Pantry extends CookInteractable {

    FoodItem.FoodID foodID;

    public Pantry(Body body, Rectangle rectangle) {
        super(body,rectangle);
    }

    public void setItem(FoodItem.FoodID foodID) {
        this.foodID = foodID;
    }

    public void interact(Cook cook, InputKey.InputTypes inputType) {
        // If the input is to pick up:
        if (inputType == InputKey.InputTypes.PICK_UP || inputType == InputKey.InputTypes.USE) {
            // Add the new FoodItem onto the stack.
            FoodItem.FoodID addedFood = foodID;
            // If the foodID is "bun", check which bun it should add.
            if (foodID == FoodItem.FoodID.bun) {
                boolean bottom = true;
                // Look through the stack, and alternate between top bun or bottom bun.
                Array<FoodItem.FoodID> foodItems = cook.foodStack.getStack();
                for (FoodItem.FoodID foodItem : foodItems) {
                    if (foodItem == FoodItem.FoodID.bottomBun) {
                        bottom = false;
                        break;
                    }
                    if (foodItem == FoodItem.FoodID.topBun) {
                        bottom = true;
                        break;
                    }
                }
                addedFood = bottom ? FoodItem.FoodID.bottomBun : FoodItem.FoodID.topBun;
            }
            cook.foodStack.addStack(addedFood);
        }
    }

    @Override
    public void update(float delta) { }

    @Override
    public void render(SpriteBatch batch) { }

    @Override
    public void renderDebug(SpriteBatch batch) {

    }

    @Override
    public void renderShape(ShapeRenderer shape) { }

    @Override
    public void renderShapeDebug(ShapeRenderer shape) {

    }
}
