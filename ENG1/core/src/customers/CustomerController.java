package customers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import food.Recipe;
import game.GameSprites;
import stations.ServingStation;

import java.util.Random;

/**
 * The class that controls how the {@link Customer}s are
 * added to the stations, what {@link food.Recipe}s they are
 * given and so on.
 */
public class CustomerController {

    /** An {@link Array} of {@link Customer}s currently waiting. */
    private Array<Customer> customers;
    /** The {@link Sprite} of the {@link Customer}. */
    private static Sprite customerSprite;
    /** An array of all {@link ServingStation}s to assign to the {@link Customer}s.*/
    private static Array<ServingStation> servingStations;
    /** The number of {@link Customer}s left to serve. */
    private int customersLeft,
    /** The number of {@link Customer}s served. */
    customerCount;

    /**
     * Constructor for the {@link CustomerController}.
     * <br>It sets up the array that the {@link Customer}s
     * will be stored in.
     */
    public CustomerController() {
        customers = new Array<>();
        customersLeft = 0;
        customerCount = 0;
        customerSprite = GameSprites.getInstance().getSprite(GameSprites.SpriteID.CUSTOMER,"0");
        customerSprite.setSize(42.5F,70);
        servingStations = new Array<>();
    }

    /**
     * A function with no input that returns whether a
     * {@link Customer} can (true) or not (false) be
     * added to a {@link ServingStation} on the map.
     * a {@link Customer} can be added to a {@link ServingStation}
     * on the {@link game.GameScreen}'s map or not.
     * or not.
     * @return
     */
    public boolean canAddCustomer() {
        Array<ServingStation> stations = servingStations;
        for (ServingStation station : stations) {
            if (station.getCustomer() != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a {@link Customer} to a {@link ServingStation}.
     * @return {@code int} : The number of instructions in the
     *                       {@link Customer}'s recipe.
     *                       <br>It is -1 if the {@link Customer} fails
     *                       to spawn.
     */
    public int addCustomer() {
        // Get a deep copy of all the ServingStations.
        Array<ServingStation> emptyStations = new Array<>(servingStations);
        // Loop through and remove all the stations that have a
        // Customer already.
        for (int i = emptyStations.size - 1 ; i >= 0 ; i--) {
            if (emptyStations.get(i).hasCustomer()) {
                emptyStations.removeIndex(i);
            }
        }
        // If there are no Stations left, return false.
        if (emptyStations.size == 0) {
            return -1;
        }
        // Now that the only stations left are the ones without Customers,
        // randomly pick one and add a customer to it.
        Random random = new Random();
        ServingStation chosenStation = emptyStations.get(random.nextInt(emptyStations.size));

        Customer newCustomer = new Customer(customerSprite,
                new Vector2(chosenStation.getCustomerX(),
                        chosenStation.getCustomerY()));
        customers.add(newCustomer);
        newCustomer.randomRecipe();
        chosenStation.setCustomer(newCustomer);
        return Recipe.firstRecipeOption(newCustomer.getRequestName()).size();
    }

    public void removeCustomer(ServingStation station) {
        // First make sure the station has a Customer
        if (!station.hasCustomer()) {
            return;
        }
        // Remove the customer from the customers array.
        customers.removeValue(station.getCustomer(),true);
        // Then, if it has a customer, set the customer of the station
        // to null.
        station.setCustomer(null);
    }

    /**
     * Sets the number of {@link Customer}s needing to be served.
     * @param customersLeft
     */
    public void setCustomersLeft(int customersLeft) {
        this.customersLeft = customersLeft;
    }

    /**
     * Gets the number of {@link Customer}s needing to be served.
     * @return {@code int} : The number of {@link Customer}s left.
     */
    public int getCustomersLeft() {
        return customersLeft;
    }

    public void updateCustomersLeft() {
        customersLeft -= 1;
    }

    /**
     * Adds a {@link ServingStation} to the {@link Array} of
     * {@link ServingStation}s so that {@link Customer}s can
     * be assigned to them.
     * @param station
     */
    public void addServingStation(ServingStation station) {
        servingStations.add(station);
    }

    /**
     * An {@link Array} of all of the {@link ServingStation}s
     * within the game.
     * @return {@link Array}&lt;{@link ServingStation}&gt; :
     *                  The {@link Array} of {@link ServingStation}s.
     */
    public Array<ServingStation> getServingStations() {
        return servingStations;
    }

    public void customerServed(ServingStation station) {
        int customerInd = customers.indexOf(station.getCustomer(),true);
        System.out.println(customerInd);
        if (customerInd < 0) {
            return;
        }
        removeCustomer(station);
        updateCustomersLeft();
    }
}
