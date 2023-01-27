package recipes;

import java.util.HashMap;

import food.FoodStack;
import food.FoodItem.FoodID;

public class Recipe {
	public enum Recipes {

	}

	/** A HashMap containing how each FoodItem's FoodID, via a station of StationID, can convert to another foodID.*/
	private static final HashMap<String, String[]> recipes = new HashMap<>();
		static {
			recipes.put("Onion Tomato Salad",
				new String[] {
					new FoodStack(FoodID.onionChop, FoodID.tomatoChop).toString(),
					new FoodStack(FoodID.tomatoChop, FoodID.onionChop).toString()
				}
			);
			recipes.put("Lettuce Tomato Salad",
				new String[] {
					new FoodStack(FoodID.lettuceChop, FoodID.tomatoChop).toString(),
					new FoodStack(FoodID.tomatoChop, FoodID.lettuceChop).toString()
				}
			);
			recipes.put("Lettuce Onion Salad",
				new String[] {
					new FoodStack(FoodID.lettuceChop, FoodID.onionChop).toString(),
					new FoodStack(FoodID.onionChop, FoodID.lettuceChop).toString()
				}
			);
			recipes.put("Plain Burger",
				new String[] {
					new FoodStack(FoodID.bun, FoodID.meatCook, FoodID.bun).toString()
				}
			);
	}
}
