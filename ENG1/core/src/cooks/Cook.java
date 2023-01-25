package cooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static helper.Constants.PPM;

public class Cook extends GameEntity{

    private Texture texture;

    public Cook(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 10f;
        this.texture = new Texture("cooks/Cook1.png");

    }

    @Override
    public void update() {
        checkUserInput();
    }

    @Override
    public void render(SpriteBatch batch) {

        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        batch.draw(texture,x-width/2,y-height/2,width,height);

    }

    private void checkUserInput()
    {
        velX = 0;
        velY = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            velX += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            velX += -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            velY += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            velY += -1;
        }

        body.setLinearVelocity(velX * speed,velY * speed);

    }
}
