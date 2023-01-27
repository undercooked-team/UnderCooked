package cooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import helper.BodyHelper;
import helper.CollisionHelper;
import org.w3c.dom.Text;

import static helper.Constants.PPM;

import food.FoodStack;
import food.FoodItem.FoodID;

public class Cook extends GameEntity {

    private Sprite sprite;
    private TextureAtlas textureAtlas;
    private CookInteractor cookInteractor;
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

    public Cook(float width, float height, Body body, CollisionHelper ch) {
        super(width, height, body);
        this.dir = Facing.DOWN;
        this.speed = 10f;
        this.textureAtlas = new TextureAtlas("cooks/cook.atlas");
        setSprite();

        float cookInteractorSize = 32;
        World world = body.getWorld();
        Rectangle interactorCollision = BodyHelper.createRectangle(this.x, this.y, cookInteractorSize, cookInteractorSize);
        // The below is just to visualise the debug square
        Body interactorBody = BodyHelper.createBody(this.x,this.y,cookInteractorSize,cookInteractorSize,true,world);
        interactorBody.setActive(false);

        this.cookInteractor = new CookInteractor(cookInteractorSize, interactorCollision, interactorBody, ch);
        // Initialize FoodStack
        this.foodStack = new FoodStack();
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

    }

    @Override
    public void render(SpriteBatch batch) {
        setSprite();
        sprite.setPosition(x*PPM-width/2,y*PPM-height/2);
        this.sprite.setSize(width+200,height); // +200 as the sprite is 190x280, but the collision box is 170x280
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
