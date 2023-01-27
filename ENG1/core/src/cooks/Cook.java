package cooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import game.GameScreen;
import game.GameSprites;
import helper.BodyHelper;

import static helper.Constants.PPM;

import food.FoodStack;
import food.FoodItem.FoodID;

public class Cook extends GameEntity {

    private Sprite sprite;
    private GameSprites gameSprites;
    private CookInteractor cookInteractor;
    private GameScreen gameScreen;
    private Facing dir;
    /** The cook's stack of things, containing all the items they're holding. Index 0 = Top Item */
    public FoodStack foodStack;

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
        this.gameSprites = gameScreen.getGameSprites();

        // Initialize FoodStack
        this.foodStack = new FoodStack();

        // Set the sprite
        setSprite();

        float cookInteractorSize = 32;
        World world = body.getWorld();
        Rectangle interactorCollision = BodyHelper.createRectangle(this.x, this.y, cookInteractorSize, cookInteractorSize);
        // The below is just to visualise the debug square
        Body interactorBody = BodyHelper.createBody(this.x,this.y,cookInteractorSize,cookInteractorSize,true,world);
        interactorBody.setActive(false);

        this.cookInteractor = new CookInteractor(cookInteractorSize, interactorCollision, interactorBody, gameScreen.getCollisionHelper());
    }

    public void userInput() {
        checkUserInput();
    }

    @Override
    public void update() {
        x = body.getPosition().x;
        y = body.getPosition().y;
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
        sprite.setPosition(x*PPM-width/2-2.5F,y*PPM-height/2); // -2.5 for a similar reason to the below one
        this.sprite.setSize(width+5,height); // + 2 * 2.5 (5) as the sprite is 19x28, but the collision box is 42.5x70 (17 * 2.5 x 28 * 2.5)
        // The reason is that when the sprite is in the holding sprite, it uses an extra pixel on either side depending on which direction
        // the cook is looking
        sprite.draw(batch);
    }

    /** Responsible for detecting user input.*/
    private void checkUserInput()
    {
        velX = 0;
        velY = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            velX += 1;
            this.dir = Facing.RIGHT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            velX += -1;
            this.dir = Facing.LEFT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            velY += 1;
            this.dir = Facing.UP;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            velY += -1;
            this.dir = Facing.DOWN;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            cookInteractor.checkCollisions(this);
        }

        body.setLinearVelocity(velX * speed,velY * speed);

    }
}
