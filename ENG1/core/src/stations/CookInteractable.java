package stations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import cooks.GameEntity;

import food.FoodItem.FoodID;
import java.util.HashMap;
import other.InteractionKey;

import static helper.Constants.PPM;

public class CookInteractable extends GameEntity {
    protected Rectangle interactRect;

    public CookInteractable(float width, float height, Body body, Rectangle rect) {
        super(width, height, body);
        this.interactRect = rect;
    }

    public Rectangle getRectangle() {
        return this.interactRect;
    }

    public void interact(Cook cook) {

        System.out.println("Ping! Interacted with station at x=" + body.getPosition().x*PPM +
                ", y=" + body.getPosition().x*PPM);

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    /// #################################################################################
    /// StationID and Interactions

    /// NOTE: Since interactions and interactoin() don't need to be in Station.java to work, it may not be very suitable here
    /// I made the choice of adding them here because a food item logically does not need to be as concerned with interactions
    /// as a station.

    /** A HashMap containing how each FoodItem's FoodID, via a station of StationID, can convert to another foodID.*/
    public static final HashMap<InteractionKey, FoodID> interactions = new HashMap<>();
    static {
        interactions.put(new InteractionKey(FoodID.lettuce, Stations.StationID.cut), FoodID.lettuceChop);
        interactions.put(new InteractionKey(FoodID.tomato, Stations.StationID.cut), FoodID.tomatoChop);
        interactions.put(new InteractionKey(FoodID.onionChop, Stations.StationID.cut), FoodID.onionChop);
        interactions.put(new InteractionKey(FoodID.meat, Stations.StationID.fry), FoodID.meatCook);
    }

    /**
    * Convert a FoodItem's foodID into another foodID using station of stationID.
    * @param foodID : The FoodID of the input ingredient.
    * @param stationID : The StationID of the station being used.
    * @return FoodID of the new ingredient, OR null if the station cannot interact with this foodID.
    */
    public FoodID interaction(FoodID foodID, Stations.StationID stationID) {
        FoodID newFood = interactions.get(new InteractionKey(foodID, stationID));
        return newFood;
    };
}
