package stations;

import customers.Customer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import cooks.Cook;
import interactions.InputKey;

/**
 * The ServingStation class, where the {@link cooks.Cook} provide
 * the {@link customers.Customer}s with their orders.
 */
public class ServingStation extends Station {

    private String request;
    private Customer customer;
    //private Customer customer;

    /**
     * The constructor for the ServingStation
     * @param rectangle The shape and location of the Station.
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
     * Any custom rendering for the {@link ServingStation}
     * @param batch The SpriteBatch renderer to render the Station in.
     */
    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}
