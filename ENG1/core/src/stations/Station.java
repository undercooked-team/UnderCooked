package stations;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import game.GameSprites;
import interactions.InputKey;

public class Station extends CookInteractable {

    /** IDs of all the different possible types of stations.*/
    public enum StationID {
        fry,
        cut,
        counter,
        bin,
        none
    }

    StationID stationID;
    boolean inUse;
    GameSprites gameSprites;

    public Station(float width, float height, Body body, Rectangle rectangle) {
        super(width,height,body,rectangle);
        inUse = false;
        this.gameSprites = GameSprites.getInstance();
    }

    public void setID(StationID stationID) {
        this.stationID = stationID;
    }

    public void interact(Cook cook, InputKey.InputTypes inputType) {
        System.out.println(stationID);

        System.out.println(cook.foodStack);
    }

    public void update(float delta) { }

    public void render(SpriteBatch batch) {
        // Render the station's item on top, when inUse is false.
        if (!inUse) {
            Sprite stationSprite = GameSprites.getInstance().getSprite(GameSprites.SpriteID.STATION,String.valueOf(stationID));
            batch.draw(stationSprite,x-35F/2,y-10F,35F,35F);
        }
    }

    @Override
    public void renderShape(ShapeRenderer shape) { }
}
