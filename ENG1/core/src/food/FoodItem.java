package food;

import java.util.HashMap;

public class FoodItem {

    /** IDs of all the different possible types of food ingredients.*/
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
        bottomBun,
        topBun,
        none
    }

    /** A dict of the pixel height for each food. Used when rendering the FoodStack.*/
    public static final HashMap<FoodID, Float> foodHeights = new HashMap<>();

    static {
        foodHeights.put(FoodID.lettuce, 20F);
        foodHeights.put(FoodID.lettuceChop, 4F);
        foodHeights.put(FoodID.tomato, 20F);
        foodHeights.put(FoodID.tomatoChop, 5.8F);
        foodHeights.put(FoodID.onion, 20F);
        foodHeights.put(FoodID.onionChop, 5.8F);
        foodHeights.put(FoodID.meat, 8F);
        foodHeights.put(FoodID.meatCook, 8F);
        foodHeights.put(FoodID.bun, 20F);
        foodHeights.put(FoodID.bottomBun, 10F);
        foodHeights.put(FoodID.topBun, 12F);
    }

    /** The ID of this FoodItem. Has a public get and set method.*/
    private FoodID foodID;
    public FoodID GetID(FoodID newID) {
        return foodID;
    }
    public void SetID(FoodID newID) {
        foodID = newID;
    }

    /** FoodItem Constructor. Creates a new FoodItem of ingredient: foodIDin
    * @param foodIDin : The ingredient you want to create a FoodItem out of.
    */
    public FoodItem(FoodID foodIDin) {
        foodID = foodIDin;
    }
}

