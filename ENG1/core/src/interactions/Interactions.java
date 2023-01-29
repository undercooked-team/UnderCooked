package interactions;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import food.FoodItem.FoodID;
import stations.Station.StationID;

public class Interactions {
    /// Since interactions and interaction() don't need to be in FoodItem.java or Station.java to work, it may not be very suitable there

    /** A HashMap containing how each FoodItem's FoodID, via a station of StationID, can convert to another foodID.*/
    private static final HashMap<String, InteractionResult> interactions = new HashMap<>();
    static {
        interactions.put(InteractionKey(FoodID.lettuce, StationID.cut), new InteractionResult(FoodID.lettuceChop,new float[] {25,50,75},-1));
        interactions.put(InteractionKey(FoodID.tomato, StationID.cut), new InteractionResult(FoodID.tomatoChop,new float[] {25,50,75},-1));
        interactions.put(InteractionKey(FoodID.onion, StationID.cut), new InteractionResult(FoodID.onionChop,new float[] {25,50,75},-1));
        interactions.put(InteractionKey(FoodID.meat, StationID.fry), new InteractionResult(FoodID.meatCook,new float[] {50},20F));
    }

    public static class InteractionResult {
        /**
         * @param result -
         *               The resulting FoodID
         * @param steps -
         *              The steps in a range of 0 - 100 of the process where input is required
         * @param speed -
         *              The speed of which the progress bar fills up for a station per second. -1 is instant.
         */
        private FoodID result;
        private float[] steps;
        private float speed;
        public InteractionResult(FoodID result, float[] steps, float speed) {
            this.result = result;
            this.steps = steps;
            this.speed = speed;
        }

        public FoodID getResult() { return result; }
        public float[] getSteps() { return steps; }
        public float getSpeed() { return speed; }
    }

    /** The different IDs of interaction. Used to get the Arrays. */
    public enum InputID {
        COOK_INTERACT,
        MENU,
        COOK_MISC, COOK_MOVEMENT
    }


    /** A HashMap containing all different forms of user inputs. These can easily
     * be changed / modified as needed from here, instead of searching through the
     * code.
     *
     * The InputKeys returned can then be looped through, and checked using the appropriate
     * Gdx.input.isKeyPressed function.
     *
     * This means dynamic key changing can be added, if you change this function from static,
     * and multiple keys can be assigned to one control.
     * */
    private static final HashMap<InputID, Array<InputKey>> inputs = new HashMap<>();
    static {
        inputs.put(InputID.MENU, new Array<>(new InputKey[]{
                new InputKey(InputKey.InputTypes.INSTRUCTIONS, Input.Keys.I),
                new InputKey(InputKey.InputTypes.RESET_GAME, Input.Keys.R),
                new InputKey(InputKey.InputTypes.START_GAME, Input.Keys.ENTER),
                new InputKey(InputKey.InputTypes.PAUSE, Input.Keys.ESCAPE),
                new InputKey(InputKey.InputTypes.UNPAUSE, Input.Keys.ESCAPE),
                new InputKey(InputKey.InputTypes.CREDITS, Input.Keys.C),
                new InputKey(InputKey.InputTypes.QUIT, Input.Keys.Q),
        }));
        inputs.put(InputID.COOK_MOVEMENT, new Array<>(new InputKey[] {
                new InputKey(InputKey.InputTypes.COOK_UP,Input.Keys.W),
                new InputKey(InputKey.InputTypes.COOK_LEFT,Input.Keys.A),
                new InputKey(InputKey.InputTypes.COOK_DOWN,Input.Keys.S),
                new InputKey(InputKey.InputTypes.COOK_RIGHT,Input.Keys.D),

                new InputKey(InputKey.InputTypes.COOK_UP,Input.Keys.UP),
                new InputKey(InputKey.InputTypes.COOK_LEFT,Input.Keys.LEFT),
                new InputKey(InputKey.InputTypes.COOK_DOWN,Input.Keys.DOWN),
                new InputKey(InputKey.InputTypes.COOK_RIGHT,Input.Keys.RIGHT)
        }));
        inputs.put(InputID.COOK_INTERACT, new Array<>(new InputKey[]{
                new InputKey(InputKey.InputTypes.USE, Input.Keys.K),
                new InputKey(InputKey.InputTypes.PICK_UP, Input.Keys.J),
                new InputKey(InputKey.InputTypes.PUT_DOWN, Input.Keys.L)
        }));
        inputs.put(InputID.COOK_MISC, new Array<>(new InputKey[] {
                new InputKey(InputKey.InputTypes.COOK_SWAP, Input.Keys.SPACE)
        }));
    }

    public static Array<InputKey.InputTypes> keysPressed = new Array<>();
    public static Array<InputKey.InputTypes> keysJustPressed = new Array<>();

    public static Array<InputKey> getInputKeys(InputID inputID) {
        return inputs.get(inputID);
    }

    public static void resetKeys() {
        keysPressed.clear();
        keysJustPressed.clear();
    }

    public static void updateKeys() {
        resetKeys();
        for (InputID inputID : InputID.values()) {
            for (InputKey inputKey : inputs.get(inputID)) {
                if (Gdx.input.isKeyPressed(inputKey.getKey())) {
                    keysPressed.add(inputKey.getType());
                }
                if (Gdx.input.isKeyJustPressed(inputKey.getKey())) {
                    keysJustPressed.add(inputKey.getType());
                }
            }
        }
    }

    public static String getKeyString(InputKey.InputTypes inputType) {
        Array<String> validKeys = new Array<>();
        for (Array<InputKey> inputKeys : inputs.values()) {
            for (InputKey inputKey : inputKeys) {
                if (inputKey.getType() == inputType) {
                    validKeys.add(getKeyString(inputKey));
                }
            }
        }
        // If there are no results, return "undefined"
        if (validKeys.size == 0) {
            return "undefined";
        // If there is 1 result, output that result alone
        } else if (validKeys.size == 1) {
            return validKeys.first();
        }

        // Otherwise, return them in an array format.
        String output = "[";
        for (int i = 0 ; i < validKeys.size ; i++) {
            output += validKeys.get(i) + (i == validKeys.size-1 ? "" : ",");
        }
        return output + "]";
    }

    public static String getKeyString(InputKey inputKey) {
        return Input.Keys.toString(inputKey.getKey());
    }

    public static boolean isPressed(InputKey.InputTypes inputType) {
        return keysPressed.contains(inputType, true);
    }

    public static boolean isJustPressed(InputKey.InputTypes inputType) {
        return keysJustPressed.contains(inputType, true);
    }


    /**
    * Convert a FoodItem's foodID into another foodID using station of stationID.
    * @param foodID : The FoodID of the input ingredient.
    * @param stationID : The StationID of the station being used.
    * @return FoodID of the new ingredient, OR null if the station cannot interact with this foodID.
    */
    public static InteractionResult interaction(FoodID foodID, StationID stationID) {
        InteractionResult newResult = interactions.get(InteractionKey(foodID, stationID));
        return newResult;
    };

    private static String InteractionKey(FoodID foodID, StationID stationID) {
        return String.format("%s-%s", foodID.ordinal(), stationID.ordinal());
    }
}
