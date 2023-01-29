package interactions;

/** The class responsible for mapping keys on the keyboard to enum constants of equivalent meaning.*/
public class InputKey {

    /** All the different inputs available in the game. */
    public enum InputTypes {
        // MENU
        START_GAME,
        RESET_GAME,
        INSTRUCTIONS,
        PAUSE,
        UNPAUSE,
        CREDITS,
        QUIT,

        // COOK_INTERACT
        PUT_DOWN,
        PICK_UP,
        USE,

        // COOK_MOVEMENT
        COOK_UP,
        COOK_RIGHT,
        COOK_DOWN,
        COOK_LEFT,

        // COOK_MISC
        COOK_SWAP
    }

    /** The key on the keyboard, represented as an int. */
    private int key;
    /** The enum constant which is representing the key above. */
    private InputTypes inputType;

    /**
     * The InputKey Constructor
     * @param inputType The InputType enum constant
     * @param key The key it correlates to
     */
    public InputKey(InputTypes inputType, int key) {
        this.key = key;
        this.inputType = inputType;
    }

    /**
     * ket getter
     * @return key
     */
    public int getKey() {
        return key;
    }

    /**
     * Get the enum constant for what this input is.
     * @return The enum constant for this input.
     */
    public InputTypes getType() {
        return inputType;
    }
}
