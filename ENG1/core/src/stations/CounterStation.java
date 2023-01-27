package stations;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import cooks.Cook;
import food.FoodItem;
import food.FoodStack;
import game.GameSprites;

import static helper.Constants.PPM;

public class CounterStation extends Station {

    FoodStack foodStack;
    GameSprites gameSprites;
    public CounterStation(float width, float height, Body body, Rectangle rectangle) {
        super(width, height, body, rectangle);
        foodStack = new FoodStack();
        this.gameSprites = GameSprites.getInstance();
    }

    @Override
    public void interact(Cook cook) {
        // Swap what the Cook and the counter are holding.
        FoodStack tempStack = foodStack;
        foodStack = cook.foodStack;
        cook.foodStack = tempStack;
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
