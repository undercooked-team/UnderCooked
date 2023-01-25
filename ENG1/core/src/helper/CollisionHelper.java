package helper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import stations.Station;
import stations.Stations;

import static helper.Constants.PPM;

public class CollisionHelper {

    protected Stations stations;

    private float distRectToStation(Rectangle rect, Station station) {
        return Util.distancePoints(rect.x-rect.getWidth()/2,
                rect.y-rect.getHeight()/2,
                station.getBody().getPosition().x*PPM,
                station.getBody().getPosition().y*PPM);
    }

    public Station getStationInteract(Rectangle rectangle) {
        Array<Station> intStations = stations.rectangleCollisions(rectangle);
        if (intStations.size == 0) {
            return null;
        }
        float closestDist = distRectToStation(rectangle, intStations.get(0));
        Station closest = intStations.get(0);
        for (int i = 1 ; i < intStations.size ; i++) {
            float dist = distRectToStation(rectangle, intStations.get(i));
            if (dist < closestDist) {
                closestDist = dist;
                closest = intStations.get(i);
            }
        }
        return closest;
    }

    public void setStations(Stations stations) {
        this.stations = stations;
    }

}
