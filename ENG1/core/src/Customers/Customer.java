package Customers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import game.GameScreen;
import helper.Constants;

public class Customer extends Sprite {

    protected GameScreen screen;
    protected World world;
    protected boolean toDestroy,destroyed;
    protected Body body;

    public Customer(GameScreen gameScreen, float x, float y)
    {
        this.world = screen.getWorld();
        setPosition(x,y);
        setBounds(getX(), getY(), 16/ Constants.PPM, 16/Constants.PPM);
        defineCustomer();
        toDestroy = false;
        destroyed = false;
    }

    public void defineCustomer()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/Constants.PPM);

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }
    public void Serve()
    {
        destroy();
    }
    public void draw(Batch batch)
    {
        if(!destroyed)
        {
            super.draw(batch);
        }
    }
    public void update(float dt)
    {
        if(toDestroy && !destroyed)
        {
            world.destroyBody(body);
            destroyed = true;
        }
        setPosition(body.getPosition().x - getWidth() /2,body.getPosition().y - getHeight()/2);

    }

    public void destroy()
    {
        toDestroy = true;
    }

}
