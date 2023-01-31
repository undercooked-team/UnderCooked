package cooks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import helper.CollisionHelper;
import interactions.InputKey;
import stations.CookInteractable;

/**
 * The Cook's in-game Collision and Detection Class
 */
public class CookInteractor {

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
     * @param x The {@link #x} coordinate of the {@link CookInteractor}.
     * @param y The {@link #y} coordinate of the {@link CookInteractor}.
     * @param size The size of CookInteractor's {@link GameEntity}.
     */
    public CookInteractor(float x, float y, float size) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.collision = new Rectangle(x,y,size,size);
        this.ch = CollisionHelper.getInstance();
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

        }
    }

    /**
     * The debug render function, used to render the {@link CookInteractor}'s
     * collision box.
     * @param shape The {@link ShapeRenderer} used to render.
     */
    public void renderDebug(ShapeRenderer shape) {
        shape.set(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.BLACK);
        shape.rect(collision.x,collision.y,size,size);
        shape.setColor(Color.WHITE);
        shape.set(ShapeRenderer.ShapeType.Filled);
    }

}
