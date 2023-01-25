package stations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.GameEntity;

import static helper.Constants.PPM;

public class Station extends GameEntity {
    protected Rectangle interactRect;
    public enum StationType {
        PREPARATION,
        PANTRY
    }

    StationType type;

    public Station(float width, float height, Body body, Rectangle rect, StationType type) {
        super(width, height, body);
        this.type = type;
        this.interactRect = rect;
    }

    public void interact() {

        System.out.println("Ping! Interacted with station at x=" + body.getPosition().x*PPM +
                ", y=" + body.getPosition().x*PPM);

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
