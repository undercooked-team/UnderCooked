package food;

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

