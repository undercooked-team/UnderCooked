package stations;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import cooks.Cook;
import food.FoodItem;
import food.FoodStack;
import game.GameSprites;
import interactions.InputKey;

public class CounterStation extends Station {

    FoodStack foodStack;
    public CounterStation(float width, float height, Body body, Rectangle rectangle) {
        super(width, height, body, rectangle);
        foodStack = new FoodStack();
    }

    @Override
    public void interact(Cook cook, InputKey.InputTypes inputType) {
        // If Cook is using the put down input, put down the item in the top of their stack
        if (cook.foodStack.size() > 0 && inputType == InputKey.InputTypes.PUT_DOWN) {
            // Take it from the cook, and add it to this counter's stack.
            foodStack.addStack(cook.foodStack.popStack());
            return;
        }
        // If Cook is using the pick up input, pick up the item on the top of this stack
        if (foodStack.size() > 0 && inputType == InputKey.InputTypes.PICK_UP) {
            // Take it from the cook, and add it to this counter's stack.
            cook.foodStack.addStack(foodStack.popStack());
            return;
        }
        // Otherwise swap the items on the use input
        if (inputType == InputKey.InputTypes.USE) {
            FoodStack tempStack = foodStack;
            // If the above doesn't apply, then just swap the stacks.
            foodStack = cook.foodStack;
            cook.foodStack = tempStack;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render using the same method as the Cook does.
        // Loop through the items in the food stack.
        // It is done from the end of the stack to the start because the stack's top is
        // at 0, and the bottom at the end.
        Array<FoodItem.FoodID> foodList = foodStack.getStack();
        float xOffset = 0F, yOffset = 0F;
        // Get offset based on direction.

        float drawX = x, drawY = y;
        /*if (foodStack.size() > 0) {
            foodStack.popStack();
        }*/
        for (int i = foodList.size-1 ; i >= 0 ; i--) {
            Sprite foodSprite = gameSprites.getSprite(GameSprites.SpriteID.FOOD, String.valueOf(foodList.get(i)));
            Float drawInc = FoodItem.foodHeights.get(foodList.get(i));
            if (drawInc == null) {
                drawY += 5F;
                continue;
            }
            foodSprite.setScale(2F);
            foodSprite.setPosition(drawX-foodSprite.getWidth()/2+xOffset,drawY+foodSprite.getHeight()/2F+yOffset);
            foodSprite.draw(batch);
            drawY += drawInc;
        }
    }
}
