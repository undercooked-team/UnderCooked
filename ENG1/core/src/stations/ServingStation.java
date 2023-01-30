package stations;

import customers.Customer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import cooks.Cook;
import interactions.InputKey;

/**
 * The {@link ServingStation} class, where the {@link cooks.Cook} provide
 * the {@link customers.Customer}s with their orders.
 */
public class ServingStation extends Station {

    private String request;
    private Customer customer;
    //private Customer customer;

    /**
     * The constructor for the {@link ServingStation}.
     * @param rectangle The collision and interaction area of the {@link ServingStation}.
     */
    public ServingStation(Rectangle rectangle) {
        super(rectangle);
    }

    /*public boolean hasCustomer() {
        return !(customer == null);
    }*/

    /*public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

    /**
     * The interact function for the {@link ServingStation}.
     *
     * <br>This checks that the {@link Cook} has the right {@link food.Recipe}, and then acts
     * based on if the {@link Cook} does or does not.
     * @param cook The cook that interacted with the {@link CookInteractable}.
     * @param inputType The type of {@link InputKey.InputTypes} the player made with
     *                  the {@link CookInteractable}.
     */
    @Override
    public void interact(Cook cook, InputKey.InputTypes inputType) {
        // USE to see request, or submit request
        if (inputType == InputKey.InputTypes.USE) {
            // First make sure there is actually a request on this counter.
            customer.setCanDraw(false);
            customer.updateCustomerCount();
            customer.addNewCustomer();
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

    /**
     * The function used to render the {@link ServingStation}.
     * @param batch The {@link SpriteBatch} used to render.
     */
    @Override
    public void render(SpriteBatch batch) {

    }

    /**
     * Set the {@link #customer} of the {@link ServingStation} to
     * a new {@link Customer}.
     * @param customer The {@link Customer} to set the {@link ServingStation} to.
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}
