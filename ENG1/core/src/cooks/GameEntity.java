package cooks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;

import static helper.Constants.PPM;

public abstract class GameEntity {

    protected float x,y,velX,velY,speed;
    protected  float width,height;
    protected Body body;
    public  GameEntity(float width, float height, Body body)
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
    public abstract void renderShape(ShapeRenderer shape);

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

