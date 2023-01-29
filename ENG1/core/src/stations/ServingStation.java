package stations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;
import interactions.InputKey;

public class ServingStation extends Station {

    private String request;
    //private Customer customer;
    public ServingStation(Rectangle rectangle) {
        super(rectangle);
    }

    /*public boolean hasCustomer() {
        return !(customer == null);
    }*/

    /*public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

    @Override
    public void interact(Cook cook, InputKey.InputTypes inputType) {
        // USE to see request, or submit request
        if (inputType == InputKey.InputTypes.USE) {
            // First make sure there is actually a request on this counter.
            if (request != null) {
                // If there is a request, then compare the two.
                if (request == cook.foodStack.toString()) {
                    // If it's correct, then the customer will take the food and leave.
                    request = null;
                    cook.foodStack.clearStack();
                    //customer.leave();
                } else {
                    // If not, then display the customer's request.
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
