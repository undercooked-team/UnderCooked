package stations;

import food.FoodItem.FoodID;
import java.util.HashMap;
import other.InteractionKey;

public class Station {
    /** IDs of all the different possible types of stations.*/
    public enum StationID {
        fry,
        cut,
        counter,
        bin
    }

    /** A HashMap containing how each FoodItem's FoodID, via a station of StationID, can convert to another foodID.*/
    public static final HashMap<InteractionKey, FoodID> interactions = new HashMap<>();
    static {
        interactions.put(new InteractionKey(FoodID.lettuce, StationID.cut), FoodID.lettuceChop);
        interactions.put(new InteractionKey(FoodID.tomato, StationID.cut), FoodID.tomatoChop);
        interactions.put(new InteractionKey(FoodID.onionChop, StationID.cut), FoodID.onionChop);
        interactions.put(new InteractionKey(FoodID.meat, StationID.fry), FoodID.meatCook);
    }

    /**
    * Convert a FoodItem's foodID into another foodID using station of stationID.
    * @param foodID : The FoodID of the input ingredient.
    * @param stationID : The StationID of the station being used.
    * @return FoodID of the new ingredient, OR null if the station cannot interact with this foodID.
    */
    public FoodID interaction(FoodID foodID, StationID stationID) {
        FoodID newFood = interactions.get(new InteractionKey(foodID, stationID));
        return newFood;
    };
}
