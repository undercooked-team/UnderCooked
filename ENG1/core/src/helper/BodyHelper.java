package helper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static helper.Constants.PPM;

public class BodyHelper {
    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic? BodyDef.BodyType.StaticBody: BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/PPM,y/PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2/ PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public static Rectangle createRectangle(float x, float y, float width, float height)
    {
        return new Rectangle(x,y,width,height);
    }

    public static boolean checkCollision(Rectangle rect1, Rectangle rect2) {
        return rect1.overlaps(rect2);
    }

    /*public static boolean checkCollision(Rectangle rect, Body body) {
        // First convert the body into a rectangle (fixed rotation is true)
        Fixture fixture = body.getFixtureList().get(0);
        PolygonShape shape = (PolygonShape) fixture.getShape();
        //Contact
        return false;
    }*/

}
