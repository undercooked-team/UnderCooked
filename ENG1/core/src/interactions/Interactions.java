package interactions;

import java.util.HashMap;
import food.FoodItem.FoodID;
import stations.Station.StationID;

public class Interactions {
    /// Since interactions and interaction() don't need to be in FoodItem.java or Station.java to work, it may not be very suitable there

    /** A HashMap containing how each FoodItem's FoodID, via a station of StationID, can convert to another foodID.*/
    private static final HashMap<String, FoodID> interactions = new HashMap<>();
    static {
        interactions.put(InteractionKey(FoodID.lettuce, StationID.cut), FoodID.lettuceChop);
        interactions.put(InteractionKey(FoodID.tomato, StationID.cut), FoodID.tomatoChop);
        interactions.put(InteractionKey(FoodID.onion, StationID.cut), FoodID.onionChop);
        interactions.put(InteractionKey(FoodID.meat, StationID.fry), FoodID.meatCook);
    }

    /**
    * Convert a FoodItem's foodID into another foodID using station of stationID.
    * @param foodID : The FoodID of the input ingredient.
    * @param stationID : The StationID of the station being used.
    * @return FoodID of the new ingredient, OR null if the station cannot interact with this foodID.
    */
    public static FoodID interaction(FoodID foodID, StationID stationID) {
        FoodID newFood = interactions.get(InteractionKey(foodID, stationID));
        return newFood;
    };

    private static String InteractionKey(FoodID foodID, StationID stationID) {
        return String.format("%s-%s", foodID.ordinal(), stationID.ordinal());
    }
}
