package interactions;

public class InputKey {

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

    private int key;
    private InputTypes inputType;

    public InputKey(InputTypes inputType, int key) {
        this.key = key;
        this.inputType = inputType;
    }

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
