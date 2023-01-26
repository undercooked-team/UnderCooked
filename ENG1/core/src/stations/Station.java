package stations;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import cooks.Cook;

public class Station extends CookInteractable {

    Stations.StationID stationID;

    public Station(float width, float height, Body body, Rectangle rectangle) {
        super(width,height,body,rectangle);
    }

    public void setID(Stations.StationID stationID) {
        this.stationID = stationID;
    }

    public void interact(Cook cook) {
        System.out.println(stationID);
    }
}
