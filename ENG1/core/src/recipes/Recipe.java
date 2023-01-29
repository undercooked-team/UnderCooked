package recipes;

import java.util.HashMap;

import com.badlogic.gdx.utils.Array;

import food.FoodStack;
import food.FoodItem.FoodID;

public class Recipe {
	/** A HashMap containing how each FoodItem's FoodID, via a station of StationID, can convert to another foodID.*/
	private static final HashMap<String, Array<String>> recipes = new HashMap<>();
		static {
			generateRecipes("Onion Tomato Salad", allCombos(FoodID.onionChop, FoodID.tomatoChop));
			generateRecipes("Lettuce Tomato Salad", allCombos(FoodID.lettuceChop, FoodID.tomatoChop));
			generateRecipes("Lettuce Onion Salad", allCombos(FoodID.lettuceChop, FoodID.onionChop));
			Array<String> plainBurger = new Array<String>();
			plainBurger.add(new FoodStack(FoodID.topBun, FoodID.meatCook, FoodID.bottomBun).toString());
			recipes.put("Plain Burger", plainBurger);

			Array<FoodID> topBunArray = new Array<FoodID>();
			topBunArray.add(FoodID.topBun);
			Array<FoodID> bottomBunArray = new Array<FoodID>();
			bottomBunArray.add(FoodID.bottomBun);

			generateRecipes("Lettuce Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.lettuceChop)
			);
			generateRecipes("Onion Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.onionChop)
			);
			generateRecipes("Tomato Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.tomatoChop)
			);
			generateRecipes("Lettuce Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.lettuceChop)
			);
			generateRecipes("Lettuce Tomato Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.lettuceChop, FoodID.tomatoChop)
			);
			generateRecipes("Lettuce Onion Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.lettuceChop, FoodID.onionChop)
			);
			generateRecipes("Tomato Onion Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.tomatoChop, FoodID.onionChop)
			);
			generateRecipes("Lettuce Tomato Onion Burger", allCombos(
				topBunArray,
				bottomBunArray,
				FoodID.meatCook, FoodID.lettuceChop, FoodID.tomatoChop, FoodID.onionChop)
			);
			/*
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
			*/
	}

	private static void generateRecipes(String recipeName, Array<Array<FoodID>> listOfFoodStacks) {
		Array<String> allValidRecipes = new Array<String>();
		for (int i = 0; i < listOfFoodStacks.size; i++) {
			Array<FoodID> recipe = listOfFoodStacks.get(i);
			allValidRecipes.add(new FoodStack(recipe).toString());
		}
		recipes.put(recipeName, allValidRecipes);
	}
	private static <T> Array<Array<T>> allCombos(T... stuff) {
		return allCombosR(new Array<T>(), new Array<T>(Array.with(stuff)));
	}
	private static <T> Array<Array<T>> allCombos(Array<T> prepend, Array<T> append, T... stuff) {
		Array<Array<T>> combos = allCombosR(new Array<T>(), new Array<T>(Array.with(stuff)));
		Array<Array<T>> newCombos = new Array<Array<T>>();
		for (Array<T> combo : combos) {
			Array<T> newCombo = new Array<T>();
			newCombo.addAll(prepend);
			newCombo.addAll(combo);
			newCombo.addAll(append);
			newCombos.add(newCombo);
		}
		return newCombos;
	}
	private static <T> Array<Array<T>> allCombosR(Array<T> myList, Array<T> remaining) {
		// If there's no remaining, add myList to storage by returning it in storage form
		if (remaining.size == 0) {
			return new Array<Array<T>>(Array.with(myList));
		}
		// We want to create a new branch for every single remaining item
		// The newList and newRemaining is carried into each branch
		Array<Array<T>> storage = new Array<Array<T>>();
		for (int i = 0; i < remaining.size; i++) {
			// Create the newList for branch i
			Array<T> newList = new Array<T>();
			newList.addAll(myList);
			newList.add(remaining.get(i));
			// Create the newRemaining for branch i
			Array<T> newRemaining = new Array<T>();
			newRemaining.addAll(remaining);
			newRemaining.removeIndex(i);
			// Create branch i and add its storage onto our storage
			storage.addAll(allCombosR(newList, newRemaining));
		}
		return storage;
	}

	// vscode debugging
	// public static void main(String[] args) {
	// 	String[] myRecipes = recipes.get("Tomato Onion Burger");
	// 	for (String myRecipe : myRecipes) {
	// 		System.out.println(myRecipe);
	// 	}
	// }
}
