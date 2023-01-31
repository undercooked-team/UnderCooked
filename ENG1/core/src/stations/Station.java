package stations;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import cooks.Cook;
import game.GameSprites;
import interactions.InputKey;

/**
 * The default {@link stations} class used as
 * a Parent for other {@link stations}.
 * */
public class Station extends CookInteractable {

    /** IDs of all the different possible types of stations.*/
    public enum StationID {
        /** Frying Station, uses {@link PreparationStation}. */
        fry,
        /** Cutting Station, uses {@link PreparationStation}. */
        cut,
        /** {@link CounterStation} that can hold {@link food.FoodItem}s. */
        counter,
        /** {@link BinStation} that can dispose of {@link food.FoodItem}s.*/
        bin,
        /** {@link ServingStation} that allows {@link Cook}s to serve {@link customers.Customer}s. */
        serving,
        /** Default Station that does nothing. */
        none
    }

    StationID stationID;
    boolean inUse;
    GameSprites gameSprites;

    /**
     * The constructor for the {@link Station}.
     * @param rectangle The collision and interaction area of the {@link Station}.
     */
    public Station(Rectangle rectangle) {
        super(rectangle);
        inUse = false;
        this.gameSprites = GameSprites.getInstance();
    }

    /**
     * Sets the {@link #stationID} of the {@link Station} to a
     * {@link StationID}.
     *
     * <br>This is not used by all Stations, but is useful if you want
     * something like that {@link PreparationStation} to function.
     * @param stationID The {@link StationID} for the {@link Station}
     *                  to be set to.
     */
    public void setID(StationID stationID) {
        this.stationID = stationID;
    }

    /**
     * The function that allows a {@link Cook} to interact with the {@link Station}.
     * <br>The default {@link Station} does nothing.
     * @param cook The cook that interacted with the {@link CookInteractable}.
     * @param inputType The type of {@link InputKey.InputTypes} the player made with
     *                  the {@link CookInteractable}.
     */
    public void interact(Cook cook, InputKey.InputTypes inputType) { }

    /**
     * An update function to be used by the {@link game.GameScreen}
     * if a {@link Station} needs it.
     * @param delta The time between frames as a float.
     */
    @Override
    public void update(float delta) { }

    /**
     * The function used to render the {@link Station}.
     * @param batch The {@link SpriteBatch} used to render.
     */
    @Override
    public void render(SpriteBatch batch) {
        // Render the station's item on top, when inUse is false.
        if (!inUse) {
            Sprite stationSprite = GameSprites.getInstance().getSprite(GameSprites.SpriteID.STATION,String.valueOf(stationID));
            batch.draw(stationSprite,x-35F/2,y-10F,35F,35F);
        }
    }

    /**
     * The function used to render the {@link Station}'s
     * debug visuals.
     * @param batch The {@link SpriteBatch} used to render.
     */
    @Override
    public void renderDebug(SpriteBatch batch) {

    }

    /**
     * The function used to render the {@link Station}.
     * @param shape The {@link ShapeRenderer} used to render.
     */
    @Override
    public void renderShape(ShapeRenderer shape) { }

    /**
     * The function used to render the {@link Station}'s
     * debug visuals.
     * @param shape The {@link ShapeRenderer} used to render.
     */
    @Override
    public void renderShapeDebug(ShapeRenderer shape) {

    }
}
