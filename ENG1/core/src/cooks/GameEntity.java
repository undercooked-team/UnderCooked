package cooks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import helper.MapHelper;

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
    protected Body body;/**
     * The constructor for the {@link GameEntity}, using a {@link Rectangle}.
     * This is to make it easier to create a {@link GameEntity} that may not
     * need a {@link Body} created elsewhere, and also allows the {@link Rectangle}
     * to be passed up to functions that need it, as a {@link Rectangle} can make a
     * {@link Body}, but not the other way around.
     * @param width The width of the {@link GameEntity} as a float.
     * @param height The height of the {@link GameEntity} as a float.
     * @param body The {@link Body} of the {@link GameEntity}.
     */
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

    /**
     * The constructor for the {@link GameEntity}, using a {@link Rectangle}.
     * This is to make it easier to create a {@link GameEntity} that may not
     * need a {@link Body} created elsewhere, and also allows the {@link Rectangle}
     * to be passed up to functions that need it, as a {@link Rectangle} can make a
     * {@link Body}, but not the other way around.
     * @param rectangle The {@link Rectangle} of the {@link GameEntity}.
     */
    public GameEntity(Rectangle rectangle) {
        this(rectangle.getWidth(),rectangle.getHeight(),MapHelper.makeBody(rectangle,true));
    }

    /**
     * The update function, used to update the {@link GameEntity}.
     * @param delta The time between frames as a float.
     */
    public abstract void update(float delta);

    /**
     * The render function, used to render the {@link GameEntity}.
     * @param batch The {@link SpriteBatch} used to render.
     */
    public abstract void render(SpriteBatch batch);

    /**
     * The debug render function, used to render the {@link GameEntity}'s
     * debug visuals.
     * @param batch The {@link SpriteBatch} used to render.
     */
    public abstract void renderDebug(SpriteBatch batch);

    /**
     * The render function, used to render the {@link GameEntity}.
     * @param shape The {@link ShapeRenderer} used to render.
     */
    public abstract void renderShape(ShapeRenderer shape);

    /**
     * The debug render function, used to render the {@link GameEntity}'s
     * debug visuals.
     * @param shape The {@link ShapeRenderer} used to render.
     */
    public abstract void renderShapeDebug(ShapeRenderer shape);

    /**
     * A getter to get the {@link GameEntity}'s {@link Body}.
     * @return {@link Body} : The {@link GameEntity}'s {@link Body}.
     */
    public Body getBody()
    {
        return body;
    }

    /**
     * A getter to get the {@link GameEntity}'s {@link #x} coordinate.
     * @return {@code float} : The {@link GameEntity}'s {@link #x}.
     */
    public float getX() {
        return x;
    }

    /**
     * A getter to get the {@link GameEntity}'s {@link #y} coordinate.
     * @return {@code float} : The {@link GameEntity}'s {@link #y}.
     */
    public float getY() {
        return y;
    }
}

