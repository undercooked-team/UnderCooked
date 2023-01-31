package stations;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import cooks.Cook;
import food.FoodItem;
import food.FoodStack;
import game.GameSprites;
import interactions.InputKey;

/**
 * The {@link CounterStation} class, where the {@link cooks.Cook}
 * can place down the {@link FoodItem}s that they are holding.
 */
public class CounterStation extends Station {

    FoodStack foodStack;

    /**
     * The constructor for the {@link CounterStation}. It sets up the
     * {@link FoodStack} for the {@link CounterStation}.
     * @param rectangle The collision and interaction area of the {@link PreparationStation}.
     */
    public CounterStation(Rectangle rectangle) {
        super(rectangle);
        foodStack = new FoodStack();
    }

    /**
     * The function that allows a {@link Cook} to interact with the {@link CounterStation}.
     *
     * <br><br>If the {@link Cook} interacts using {@link InputKey.InputTypes#USE},
     * they will swap stacks with the {@link CounterStation}.
     *
     * <br><br>If the {@link Cook} interacts using {@link InputKey.InputTypes#PICK_UP},
     * they will take the top {@link FoodItem} of the {@link FoodStack}
     * from {@link CounterStation} and put it on their own {@link FoodStack}.
     *
     * <br><br>If the {@link Cook} interacts using {@link InputKey.InputTypes#PUT_DOWN},
     * they will take the top {@link FoodItem} of their {@link FoodStack}
     * put it on the {@link CounterStation}'s {@link FoodStack}.
     * @param cook The cook that interacted with the {@link CookInteractable}.
     * @param inputType The type of {@link InputKey.InputTypes} the player made with
     *                  the {@link CookInteractable}.
     */
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

    /**
     * The function used to render the {@link PreparationStation}.
     *
     * <br>This function renders the {@link FoodStack} in a similar way
     * as the {@link Cook} renders their own.
     * @param batch The {@link SpriteBatch} used to render.
     */
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
