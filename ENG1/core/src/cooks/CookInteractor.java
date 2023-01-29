package cooks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import helper.CollisionHelper;
import interactions.InputKey;
import stations.CookInteractable;

// import static cooks.Cook.OFFSET_Y;
import static helper.Constants.PPM;

/**
 * The Cook's in-game Collision and Detection Class
 */
public class CookInteractor extends GameEntity {

    /** The X and Y Coordinates of this {@link CookInteractor}. */
    protected float x,y;
    /** The size of this {@link CookInteractor}. */
    protected float size;
    /** The rectangle responsible for collision in {@link CookInteractor}. */
    protected Rectangle collision;
    /** The Collision Helper Singleton. */
    protected CollisionHelper ch;
    /**
     * CookInteractor Constructor.
     * @param size The size of CookInteractor's {@link GameEntity}.
     * @param collision The rectangle responsible for this CookInteractor's collision.
     * @param body The body for this CookInteractor's collision.
     * @param ch The collision helper singleton.
     */
    public CookInteractor(float size, Rectangle collision, Body body, CollisionHelper ch) {
        super(size,size,body);
        this.size = size;
        this.collision = collision;
        this.ch = ch;
    }

    /**
     * Returns an X offset depending on the cook's direction.
     * Used during collision detection between the cook and other interactables.
     * @param dir The direction the cook can face
     * @return An X offset in pixels.
     */
    private float relativeX(Cook.Facing dir) {
        switch (dir) {
            case RIGHT:
                return 38.4F;
            case LEFT:
                return -38.4F;
            default:
                return 0F;
        }
    }

    /**
     * Returns an Y offset depending on the cook's direction.
     * Used during collision detection between the cook and other interactables.
     * @param dir The direction the cook can face
     * @return An Y offset in pixels.
     */
    private float relativeY(Cook.Facing dir) {
        switch (dir) {
            case UP:
                return 25.6F;
            case DOWN:
                return -25.6F;
            case RIGHT:
            case LEFT:
                return 12.8F;
            default:
                return 0F;
        }
    }

    /**
     * Update the Position of the CookInteractor to the next x, y position
     * and given direction
     * @param x New X position
     * @param y New Y position
     * @param dir New direction
     */
    protected void updatePosition(float x, float y, Cook.Facing dir) {
        float relX = relativeX(dir);
        float relY = relativeY(dir);

        this.x = x + relX;
        this.y = y + relY;

        this.collision.x = this.x - collision.width/2;
        this.collision.y = this.y - collision.height/2;

        this.x /= PPM;
        this.y /= PPM;

        this.body.setTransform(this.x,this.y,this.body.getAngle());
    }

    /**
     * Check for any collisions the CookInteractor has made
     * @param cook The cook
     * @param inputType The enum constant of the input made
     */
    public void checkCollisions(Cook cook, InputKey.InputTypes inputType) {
        CookInteractable interactStation = ch.getInteract(cook, collision);
        if (interactStation != null) {
            interactStation.interact(cook, inputType);
        } /*else {
            System.out.println("Failed");
        }*/
    }

    @Override
    public void update(float delta) { }

    @Override
    public void render(SpriteBatch batch) { }

    @Override
    public void renderShape(ShapeRenderer shape) { }

}
