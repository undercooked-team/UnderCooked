package food;

import java.util.ArrayList;

import food.FoodItem.FoodID;

public class FoodStack {
    /** The cook's stack of things, containing all the items they're holding. Index 0 = Top Item */
    private ArrayList<FoodID> foodStack;

    /**
     * Constructor for FoodStack. Can accept multiple FoodID paramters to
     * initialize the foodStack with.
     * @param foods A list of ingredients to add to the foodStack. Index 0 = Top.
     */
    public FoodStack(FoodID... foods) {
        this(new ArrayList<FoodID>());
        for (FoodID foodID : foods) {
            foodStack.add(foodID);
        }
    }

    public FoodStack(ArrayList<FoodID> foodStack) {
        this.foodStack = foodStack;
    }

    /**
    * Get the item at the top of the stack without removing it.
    * @return The item at the top of the foodstack OR `null` if there's no items in it.
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
    * @return The item at the top of the foodstack OR `null` if there's no items in it.
    */
    public FoodID popStack() {
      try {
          return foodStack.remove(0);
      } catch (IndexOutOfBoundsException e) {
          return null;
      }
    }
    /**
    * Add an item to the top of the stack.
    * @param newFood The item to add to the top of the foodstack.
    */
    public void addStack(FoodID newFood) {
        foodStack.add(0, newFood);
    }
    /**
    * foodStack getter
    * @return foodStack
    */
    public ArrayList<FoodID> getStack() {
        return foodStack;
    }
    /**
    * foodStack setter
    * @param newStack The new foodStack
    */
    public void setStack(ArrayList<FoodID> newStack) {
        foodStack = newStack;
    }
    /**
    * Returns the number of items on the foodStack.
    * @return Size of the foodStack
    */
    public int size() { return foodStack.size(); }
    /**
    * Get a string of the foodStack.
    * @return Returns a string containing the items in the foodStack. Index 0 = Top FoodItem
    */
    public String toString() {
        return foodStack.toString();
    }
}
