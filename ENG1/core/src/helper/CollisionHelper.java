package helper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import cooks.Cook;
import game.Boot;
import game.GameScreen;
import game.ScreenController;
import stations.CookInteractable;
// import stations.Station;

import static helper.Constants.PPM;

public class CollisionHelper {

    protected GameScreen gameScreen;
    private static CollisionHelper INSTANCE;

    public CollisionHelper() { }
    public static CollisionHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CollisionHelper();
        }
        return INSTANCE;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private float distRectToInteractable(Rectangle rect, CookInteractable station) {
        return Util.distancePoints(rect.x-rect.getWidth()/2,
                rect.y-rect.getHeight()/2,
                station.getBody().getPosition().x*PPM,
                station.getBody().getPosition().y*PPM);
    }

    public CookInteractable getInteract(Cook cook, Rectangle rectangle) {
        Array<CookInteractable> intStations = gameScreen.stationCollisions(rectangle);
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
