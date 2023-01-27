package cooks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import helper.CollisionHelper;
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
                return 1.2F;
            case LEFT:
                return -1.2F;
            default:
                return 0F;
        }
    }

    private float relativeY(Cook.Facing dir) {
        switch (dir) {
            case UP:
                return 0.8F;
            case DOWN:
                return -0.8F;
            case RIGHT:
            case LEFT:
                return 0.4F;
            default:
                return 0F;
        }
    }

    protected void updatePosition(float x, float y, Cook.Facing dir) {
        float relX = relativeX(dir);
        float relY = relativeY(dir);

        this.x = x + relX;
        this.y = y + relY;

        this.collision.x = this.x * PPM - collision.width/2;
        this.collision.y = this.y * PPM - collision.height/2;

        this.body.setTransform(this.x,this.y,this.body.getAngle());
    }

    public void checkCollisions(Cook cook) {
        System.out.println("Attempting to interact...");
        CookInteractable interactStation = ch.getInteract(cook, collision);
        if (interactStation != null) {
            interactStation.interact(cook);
        } else {
            System.out.println("Failed!");
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

}
