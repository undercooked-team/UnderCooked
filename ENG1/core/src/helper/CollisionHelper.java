package helper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import cooks.Cook;
import game.GameScreen;
import stations.CookInteractable;

import static helper.Constants.PPM;

/** The CollisionHelper Singleton class. */
public class CollisionHelper {

    /** The GameScreen to do collision-helping on. */
    protected GameScreen gameScreen;
    /** Contains the singleton instance. */
    private static CollisionHelper INSTANCE;

    /** Use this to either instantiate or get CollisionHelper instance.
     * @return The CollisionHelper instance.
    */
    public static CollisionHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CollisionHelper();
        }
        return INSTANCE;
    }

    /**
     * Set the gameScreen that the CollisionHelper is using.
     * @param gameScreen The desired GameScreen to use CollisionHelper on.
     */
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * The distance between a Rectangle to a CookInteractable.
     * @param rect The rectangle
     * @param station The CookInteractable (usually a station)
     * @return float: The distance between the Rectangle and CookInteractable
     */
    private float distRectToInteractable(Rectangle rect, CookInteractable station) {
        return Util.distancePoints(rect.x-rect.getWidth()/2,
                rect.y-rect.getHeight()/2,
                station.getBody().getPosition().x*PPM,
                station.getBody().getPosition().y*PPM);
    }

    /**
     * Get a list of stations within the collision rectangle.
     * @param collision The collision rectangle
     * @return A list of stations in the collision rectangle.
     */
    public Array<CookInteractable> stationCollisions(Rectangle collision) {
        Array<CookInteractable> output = new Array<>();
        for (CookInteractable cookInteractable : gameScreen.getInteractables()) {
            if (collision.overlaps(cookInteractable.getRectangle())) {
                output.add(cookInteractable);

            }
        }
        return output;
    }

    /**
     * Returns the CookInteractable closest to the specified cook
     * @param cook The cook
     * @param rectangle The cook's collisionBox
     * @return The closest CookInteractable / Station
     */
    public CookInteractable getInteract(Cook cook, Rectangle rectangle) {
        Array<CookInteractable> intStations = stationCollisions(rectangle);
        if (intStations.size == 0) {
            return null;
        }
        Rectangle cookRect = new Rectangle(cook.getX(), cook.getY(),0,0);
        float closestDist = distRectToInteractable(cookRect, intStations.get(0));
        CookInteractable closest = intStations.get(0);
        for (int i = 1 ; i < intStations.size ; i++) {
            float dist = distRectToInteractable(cookRect, intStations.get(i));
            if (dist < closestDist) {

                closestDist = dist;
                closest = intStations.get(i);
            }
        }
        return closest;
    }

}
