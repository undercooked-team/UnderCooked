package interactions;

import java.util.HashMap;
import food.FoodItem.FoodID;
import stations.Stations.StationID;

public class Interactions {
    /// Since interactions and interaction() don't need to be in Station.java to work, it may not be very suitable here
    /// I made the choice of adding them here because a food item logically does not need to be as concerned with interactions
    /// as a station.

    /** A HashMap containing how each FoodItem's FoodID, via a station of StationID, can convert to another foodID.*/
    public static final HashMap<InteractionKey, FoodID> interactions = new HashMap<>();
    static {
        interactions.put(new InteractionKey(FoodID.lettuce, StationID.cut), FoodID.lettuceChop);
        interactions.put(new InteractionKey(FoodID.tomato, StationID.cut), FoodID.tomatoChop);
        interactions.put(new InteractionKey(FoodID.onion, StationID.cut), FoodID.onionChop);
        interactions.put(new InteractionKey(FoodID.meat, StationID.fry), FoodID.meatCook);
    }

    /**
    * Convert a FoodItem's foodID into another foodID using station of stationID.
    * @param foodID : The FoodID of the input ingredient.
    * @param stationID : The StationID of the station being used.
    * @return FoodID of the new ingredient, OR null if the station cannot interact with this foodID.
    */
    public static FoodID interaction(FoodID foodID, StationID stationID) {
        FoodID newFood = interactions.get(new InteractionKey(foodID, stationID));
        return newFood;
    };
}