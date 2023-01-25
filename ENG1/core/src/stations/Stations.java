package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class Stations {
    public enum StationID{
        FRY,
        CUT,
        COUNTER,
        BIN
    }

    Array<Station> stations;

    public Stations() {
        this.stations = new Array<>();
    }

    public void addStation(Station station) {
        if (stations.contains(station, true)) {
            return;
        }
        stations.add(station);
    }

    // Returns an array of all stations being collided with
    public Array<Station> rectangleCollisions(Rectangle rectangle) {
        System.out.println("Creating array...");
        Array<Station> output = new Array<Station>();
        for (Station station : stations) {
            System.out.println(station);
            if (station.interactRect.overlaps(rectangle)) {
                output.add(station);
            }
        }
        System.out.println("Output: " + output);
        return output;
    }
}
