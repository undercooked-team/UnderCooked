package cooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import food.FoodItem;
import game.GameScreen;
import game.GameSprites;
import helper.BodyHelper;

import static helper.Constants.PPM;

import food.FoodStack;
import food.FoodItem.FoodID;
import interactions.InputKey;
import interactions.Interactions;

import java.util.ArrayList;

public class Cook extends GameEntity {

    private Sprite sprite;
    private float spriteHeight, spriteWidth;
    private GameSprites gameSprites;
    private CookInteractor cookInteractor;
    private GameScreen gameScreen;
    private Facing dir;
    /** The cook's stack of things, containing all the items they're holding. Index 0 = Top Item */
    public FoodStack foodStack;
    private Array<Facing> inputs;
    protected static final float OFFSET_Y = 27F; // Offset for the sprites after changing the collision of the Cook

    enum Facing {
        RIGHT,
        LEFT,
        UP,
        DOWN,
        NONE
    }

    public Cook(float width, float height, Body body, GameScreen gameScreen) {
        super(width, height, body);
        this.dir = Facing.DOWN;
        this.speed = 10f;
        this.gameScreen = gameScreen;
        this.gameSprites = GameSprites.getInstance();

        // Initialize FoodStack
        this.foodStack = new FoodStack();

        // Input array, with the order of inputs the user has in what direction.
        // The oldest button pressed is the one used. Pressing the opposite key removes them.
        this.inputs = new Array<>();

        // Set the sprite
        this.setSprite();

        float cookInteractorSize = 32;
        Rectangle interactorCollision = BodyHelper.createRectangle(this.x, this.y, cookInteractorSize, cookInteractorSize);
        // The below is just to visualise the debug square
        Body interactorBody = BodyHelper.createBody(this.x,this.y,cookInteractorSize,cookInteractorSize,true,body.getWorld());
        interactorBody.setActive(false);

        this.cookInteractor = new CookInteractor(cookInteractorSize, interactorCollision, interactorBody, gameScreen.getCollisionHelper());
    }

    public void userInput() {
        checkUserInput();
    }

    @Override
    public void update(float delta) {
        x = body.getPosition().x*PPM;
        y = body.getPosition().y*PPM;
        this.cookInteractor.updatePosition(x,y,dir);
    }

    private void setSprite() {
        // Set up sprite string
        String spriteName = "";
        // If holding something, add "h" to the start of the sprite name.
        if (foodStack.size() > 0) {
            spriteName += "h";
        }
        sprite = gameSprites.getSprite(GameSprites.SpriteID.COOK, spriteName + dir);
    }

    @Override
    public void render(SpriteBatch batch) {
        setSprite();
        sprite.setPosition(x-width/2-2.5F,y-height/2); // -2.5 for a similar reason to the below one
        this.sprite.setSize(47.5F,70);

        // If the cook is looking anywhere but down, draw the food first
        if (dir != Facing.DOWN) {
            renderFood(batch);
            sprite.draw(batch);
        } else {
            sprite.draw(batch);
            renderFood(batch);
        }
    }

    @Override
    public void renderShape(ShapeRenderer shape) { }

    private float foodRelativeX(Cook.Facing dir) {
        switch (dir) {
            case RIGHT:
                return 30F;
            case LEFT:
                return -30F;
            default:
                return 0F;
        }
    }

    private float foodRelativeY(Cook.Facing dir) {
        switch (dir) {
            case UP:
                return -14F;
            case DOWN:
                return -25F;
            case LEFT:
            case RIGHT:
                return -24F;
            default:
                return 0F;
        }
    }

    private void renderFood(SpriteBatch batch) {
        // Loop through the items in the food stack.
        // It is done from the end of the stack to the start because the stack's top is
        // at 0, and the bottom at the end.
        Array<FoodID> foodList = foodStack.getStack();
        float xOffset = foodRelativeX(dir), yOffset = foodRelativeY(dir);
        // Get offset based on direction.

        float drawX = x, drawY = y + OFFSET_Y;
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

    private Facing opposite(Facing direction) {
        switch(direction) {
            case UP:
                return Facing.DOWN;
            case DOWN:
                return Facing.UP;
            case RIGHT:
                return Facing.LEFT;
            case LEFT:
                return Facing.RIGHT;
            default:
                return Facing.NONE;
        }
    }

    private Facing rotate90c(Facing direction) {
        switch(direction) {
            case UP:
                return Facing.RIGHT;
            case DOWN:
                return Facing.LEFT;
            case RIGHT:
                return Facing.DOWN;
            case LEFT:
                return Facing.UP;
            default:
                return Facing.NONE;
        }
    }

    private void setDir() {
        // If the size of inputs is 0, just return and change nothing.
        if (inputs.size == 0) { return; }

        // Possible next direction is the direction that was just input
        Facing possibleNext = inputs.get(inputs.size-1);
        Facing possibleOpp = opposite(possibleNext);
        // If there is the opposite input...
        if (inputs.contains(possibleOpp, true)) {
            // Now check that the same does not apply to the other directions.
            boolean hasPossibleRotated = inputs.contains(rotate90c(possibleNext), true),
                    hasOppRotated = inputs.contains(rotate90c(possibleOpp),true);
            if (hasPossibleRotated ^ hasOppRotated) {
                // If it doesn't, set the direction to the one that is there.
                if (hasPossibleRotated) {
                    dir = rotate90c(possibleNext);
                } else {
                    dir = rotate90c(possibleOpp);
                }
            }
            // If both or neither of them are there, then change nothing.
        } else {
            // If the opposite isn't there, it's fine to switch.
            dir = possibleNext;
        }
    }

    /** Responsible for detecting user input.*/
    private void checkUserInput()
    {
        velX = 0F;
        velY = 0F;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            velX += 1;
            if (!inputs.contains(Facing.RIGHT, true)) {
                inputs.add(Facing.RIGHT);
            }
        } else {
            inputs.removeValue(Facing.RIGHT,true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            velX += -1;
            if (!inputs.contains(Facing.LEFT, true)) {
                inputs.add(Facing.LEFT);
            }
        } else {
            inputs.removeValue(Facing.LEFT,true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            velY += 1;
            if (!inputs.contains(Facing.UP, true)) {
                inputs.add(Facing.UP);
            }
        } else {
            inputs.removeValue(Facing.UP,true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            velY += -1;
            if (!inputs.contains(Facing.DOWN, true)) {
                inputs.add(Facing.DOWN);
            }
        } else {
            inputs.removeValue(Facing.DOWN,true);
        }

        setDir();

        for (InputKey inputKey : Interactions.getInputKeys(Interactions.InputID.INTERACT)) {
            if (Gdx.input.isKeyJustPressed(inputKey.getKey())) {
                cookInteractor.checkCollisions(this, inputKey.getType());
            }
        }
        /*if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            ArrayList<FoodID> foodItems = foodStack.getStack();
            for (int i = foodStack.size()-1 ; i >= 0 ; i--) {
                foodItems.remove(i);
            }
        }*/

        body.setLinearVelocity(velX * speed,velY * speed);
    }
}
