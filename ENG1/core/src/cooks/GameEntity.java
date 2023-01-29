package cooks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;

import static helper.Constants.PPM;

/** The class for any object that can exist in the game world. */
public abstract class GameEntity {

    /** X and Y position coordinates.*/
    protected float x,y;
    /** X and Y velocity of this GameEntity.*/
    protected float velX,velY;
    /** Speed of this GameEntity. */
    protected float speed;
    /** The width and height in pixels of this GameEntity.*/
    protected  float width,height;
    /** The body of this GameEntity. */
    protected Body body;
    public GameEntity(float width, float height, Body body)
    {
        this.x = body.getPosition().x * PPM;
        this.y = body.getPosition().y * PPM;
        this.width = width;
        this.height = height;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
        this.body = body;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);
    public abstract void renderDebug(SpriteBatch batch);
    public abstract void renderShape(ShapeRenderer shape);
    public abstract void renderShapeDebug(ShapeRenderer shape);

    public Body getBody()
    {
        return body;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

