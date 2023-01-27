package food;

import java.util.ArrayList;

import food.FoodItem.FoodID;

public class FoodStack {
    /** The cook's stack of things, containing all the items they're holding. Index 0 = Top Item */
    private ArrayList<FoodID> foodStack;

    /// #################################################################################
    /// foodStack and methods

    public FoodStack() {
        this(new ArrayList<FoodID>());
    }

    public FoodStack(ArrayList<FoodID> foodStack) {
        this.foodStack = foodStack;
    }

    public FoodID peekStack() {
        try {
            return foodStack.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    public FoodID popStack() {
      try {
          return foodStack.remove(0);
      } catch (IndexOutOfBoundsException e) {
          return null;
      }
    }
    public void addStack(FoodID newFood) {
        foodStack.add(0, newFood);
    }
    public ArrayList<FoodID> getStack() {
        return foodStack;
    }
    public void setStack(ArrayList<FoodID> newStack) {
        foodStack = newStack;
    }
    public int size() { return foodStack.size(); }
}
