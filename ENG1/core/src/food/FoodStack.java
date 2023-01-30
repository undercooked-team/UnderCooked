package food;

import com.badlogic.gdx.utils.Array;
import food.FoodItem.FoodID;

/**
 * A class to create the behaviour of a {@code Stack} for
 * {@link FoodItem}s to be used by the {@link cooks.Cook},
 * {@link stations.CounterStation} and {@link Recipe}s.
 */
public class FoodStack {
    /** The cook's stack of things, containing all the items they're holding. Index 0 = Top Item.
     * Has a public getter and setter.
    */
    private Array<FoodID> foodStack;

    /**
     * FoodStack Constructor. Can accept multiple FoodID parameters to
     * initialize the foodStack with.
     * @param foods A list of ingredients to add to the foodStack. Index 0 = Top.
     */
    public FoodStack(FoodID... foods) {
        this(new Array<FoodID>());
        for (FoodID foodID : foods) {
            foodStack.add(foodID);
        }
    }

    /** FoodStack Constructor. Creates a blank foodStack.*/
    public FoodStack() {
        this(new Array<FoodID>());
    }

    /**
     * FoodStack Constructor. Creates a foodStack out of the argument.
     * @param foodStack The LibGDX Array of FoodIDs which will become a FoodStack.
     */
    public FoodStack(Array<FoodID> foodStack) {
        this.foodStack = foodStack;
    }

    /**
    * Get the item at the top of the stack without removing it.
    * @return The item at the top of the foodStack OR `null` if there's no items in it.
    */
    public FoodID peekStack() {
        try {
            return foodStack.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    /**
    * Get the item at the top of the stack and remove it if there is an item in the foodStack.
    * @return The item at the top of the foodStack OR `null` if there's no items in it.
    */
    public FoodID popStack() {
      try {
          return foodStack.removeIndex(0);
      } catch (IndexOutOfBoundsException e) {
          return null;
      }
    }
    /**
    * Add an item to the top of the stack.
    * @param newFood The item to add to the top of the foodStack.
    */
    public void addStack(FoodID newFood) {
        foodStack.insert(0, newFood);
    }
    /**
    * foodStack getter
    * @return foodStack
    */
    public Array<FoodID> getStack() {
        return foodStack;
    }

    /**
     * Removes all items from the stack
     */
    public void clearStack() {
        foodStack.clear();
    }

    /**
    * foodStack setter
    * @param newStack The new foodStack
    */
    public void setStack(Array<FoodID> newStack) {
        foodStack = newStack;
    }
    /**
    * Returns the number of items on the foodStack.
    * @return Size of the foodStack
    */
    public int size() { return foodStack.size; }
    /**
    * Get a string of the foodStack.
    * @return Returns a string containing the items in the foodStack. Index 0 = Top FoodItem
    */
    public String toString() {
        return foodStack.toString();
    }
}
