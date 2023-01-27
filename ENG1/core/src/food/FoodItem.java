package food;

import java.util.HashMap;

public class FoodItem {
    /**
    * IDs of all the different possible types of food ingredients.
    */
    public enum FoodID {
        lettuce,
        lettuceChop,
        tomato,
        tomatoChop,
        onion,
        onionChop,
        meat,
        meatCook,
        bun,
        none
    }

    public static final HashMap<FoodID, Integer> foodHeights = new HashMap<>();

    static {
        foodHeights.put(FoodID.lettuce, 2);
        foodHeights.put(FoodID.lettuceChop, 1);
        foodHeights.put(FoodID.tomato, 2);
        foodHeights.put(FoodID.tomatoChop, 1);
        foodHeights.put(FoodID.onion, 2);
        foodHeights.put(FoodID.onionChop, 1);
        foodHeights.put(FoodID.meat, 1);
        foodHeights.put(FoodID.meatCook, 1);
        foodHeights.put(FoodID.bun, 1);
    }

    /**
    * The ID of this FoodItem. Has a public get and set.
    */
    private FoodID foodID;
    public FoodID GetID(FoodID newID) {
        return foodID;
    }
    public void SetID(FoodID newID) {
        foodID = newID;
    }

    /**
    * FoodItem Constructor. Creates a new FoodItem of ingredient: foodIDin
    * @param foodIDin : The ingredient you want to create a FoodItem out of.
    * @return FoodItem
    */
    public FoodItem(FoodID foodIDin) {
        foodID = foodIDin;
    }
}

