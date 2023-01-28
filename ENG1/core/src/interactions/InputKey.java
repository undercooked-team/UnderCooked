package interactions;

public class InputKey {

    public enum InputTypes {
        PUT_DOWN,
        PICK_UP,
        USE,
        RESET_GAME,
        INSTRUCTIONS,
        PLAY_GAME,
        COOK_UP,
        COOK_RIGHT,
        COOK_DOWN,
        COOK_LEFT,
        COOK_SWAP,
        PAUSE,
        UNPAUSE
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

    public InputTypes getType() {
        return inputType;
    }
}
