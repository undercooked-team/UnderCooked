package recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

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
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.bottomBun).toString()
				}
			);
			recipes.put("Lettuce Burger",
				new String[] {
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.lettuceChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.lettuceChop, FoodID.meatCook, FoodID.bottomBun).toString()
				}
			);
			recipes.put("Onion Burger",
				new String[] {
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.onionChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.onionChop, FoodID.meatCook, FoodID.bottomBun).toString()
				}
			);
			recipes.put("Tomato Burger",
				new String[] {
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.tomatoChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.tomatoChop, FoodID.meatCook, FoodID.bottomBun).toString()
				}
			);
			recipes.put("Lettuce Onion Burger",
				new String[] {
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.lettuceChop, FoodID.onionChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.onionChop, FoodID.lettuceChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.lettuceChop, FoodID.meatCook, FoodID.onionChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.onionChop, FoodID.meatCook, FoodID.lettuceChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.onionChop, FoodID.lettuceChop, FoodID.meatCook, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.lettuceChop, FoodID.onionChop, FoodID.meatCook, FoodID.bottomBun).toString()
				}
			);
			recipes.put("Lettuce Onion Burger",
				new String[] {
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.lettuceChop, FoodID.onionChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.onionChop, FoodID.lettuceChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.lettuceChop, FoodID.meatCook, FoodID.onionChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.onionChop, FoodID.meatCook, FoodID.lettuceChop, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.onionChop, FoodID.lettuceChop, FoodID.meatCook, FoodID.bottomBun).toString(),
					new FoodStack(FoodID.topBun, FoodID.lettuceChop, FoodID.onionChop, FoodID.meatCook, FoodID.bottomBun).toString()
				}
			);
	}

	private <T> ArrayList<ArrayList<T>> allCombos(T... stuff) {
		ArrayList<T> stuffList = new ArrayList<T>(Arrays.asList(stuff));
		ArrayList<ArrayList<T>> storage = new ArrayList<ArrayList<T>>();
		allCombosR(storage, stuffList);
		return storage;
	}
	private <T> ArrayList<T> allCombosR(ArrayList<ArrayList<T>> storage, ArrayList<T> remaining) {
		ArrayList<T> myList = new ArrayList<T>();
		for (int i = 0; i < remaining.size()-1; i++) {
			myList.add(remaining.get(i));
			ArrayList<T> newRemaining = (ArrayList<T>) remaining.clone();
			newRemaining.remove(i);
			myList.addAll(allCombosR(storage, newRemaining));
			storage.add(myList);
		}
		return myList;
	}
	// public static void main(String[] args) {
	// 	ArrayList<ArrayList<FoodID>> list = allCombos(FoodID.meatCook, FoodID.lettuceChop, FoodID.onionChop);
	// 	System.out.println(list);
	// }
}
