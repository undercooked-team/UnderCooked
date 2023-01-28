package cooks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import helper.CollisionHelper;
import interactions.InputKey;
import stations.CookInteractable;

import static cooks.Cook.OFFSET_Y;
import static helper.Constants.PPM;

public class CookInteractor extends GameEntity {

    protected float x,y;
    protected float size;
    protected Rectangle collision;
    protected CollisionHelper ch;
    public CookInteractor(float size, Rectangle collision, Body body, CollisionHelper ch) {
        super(size,size,body);
        this.size = size;
        this.collision = collision;
        this.ch = ch;
    }

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

    public void checkCollisions(Cook cook, InputKey.InputTypes inputType) {
        System.out.println("Attempting to interact...");
        CookInteractable interactStation = ch.getInteract(cook, collision);
        if (interactStation != null) {
            interactStation.interact(cook, inputType);
        } else {
            System.out.println("Failed!");
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void renderShape(ShapeRenderer shape) {

    }

}
