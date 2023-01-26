package helper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import game.GameScreen;
import stations.CookInteractable;
import stations.Stations;

import static helper.Constants.PPM;

public class CollisionHelper {

    protected GameScreen gameScreen;

    public CollisionHelper(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private float distRectToInteractable(Rectangle rect, CookInteractable station) {
        return Util.distancePoints(rect.x-rect.getWidth()/2,
                rect.y-rect.getHeight()/2,
                station.getBody().getPosition().x*PPM,
                station.getBody().getPosition().y*PPM);
    }

    public CookInteractable getInteract(Rectangle rectangle) {
        Array<CookInteractable> intStations = gameScreen.stationCollisions(rectangle);
        if (intStations.size == 0) {
            return null;
        }
        float closestDist = distRectToInteractable(rectangle, intStations.get(0));
        CookInteractable closest = intStations.get(0);
        for (int i = 1 ; i < intStations.size ; i++) {
            float dist = distRectToInteractable(rectangle, intStations.get(i));
            if (dist < closestDist) {
                closestDist = dist;
                closest = intStations.get(i);
            }
        }
        return closest;
    }

    /*public void setStations(Stations stations) {
        this.stations = stations;
    }*/

}
