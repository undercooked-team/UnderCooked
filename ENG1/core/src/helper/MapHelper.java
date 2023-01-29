package helper;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.Map;
import cooks.Cook;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import food.FoodItem;
import game.GameScreen;
import stations.*;

import static helper.Constants.PPM;

/** The MapHelper Class.*/
public class MapHelper {
    private GameScreen gameScreen;
    private TiledMap tiledMap;
    private Vector2 ServingStationPosition;
    private static MapHelper INSTANCE;

    public MapHelper() { }

    public static MapHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapHelper();
        }
        return INSTANCE;
    }

    public static MapHelper newInstance() {
        INSTANCE = new MapHelper();
        return INSTANCE;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public OrthogonalTiledMapRenderer setupMap()
    {
        tiledMap = new TmxMapLoader().load("Maps/StationsMap.tmx");
        parseMapObjects(tiledMap.getLayers().get("Objects").getObjects());
        return new OrthogonalTiledMapRenderer(tiledMap);
    }



    private void createStaticBody(PolygonMapObject polygonMapObject)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject)
    {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for(int i = 0;i<vertices.length / 2;i++)
        {
            Vector2 current = new Vector2(vertices[i * 2]/PPM, vertices[i*2+1]/PPM);
            worldVertices[i] = current;
        }
        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;

    }

    public static Body makeBody(Rectangle rectangle, boolean isStatic) {
        return BodyHelper.createBody(rectangle.x + rectangle.getWidth() /2, rectangle.y +rectangle.getHeight()/2,rectangle.getWidth(), rectangle.getHeight(),isStatic, INSTANCE.gameScreen.getWorld());
    }

    private void parseMapObjects(MapObjects mapObjects)
    {
        for(MapObject mapObject:mapObjects)
        {
            if(mapObject instanceof PolygonMapObject)
            {
                createStaticBody((PolygonMapObject) mapObject);
            }

            if(mapObject instanceof RectangleMapObject)
            {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();

                if(rectangleName.equals("CookStart"))
                {
                    Body body = makeBody(rectangle, false);
                    int cookInd = gameScreen.addCook(new Cook(rectangle.getWidth(), rectangle.getHeight(), body, gameScreen));
                    gameScreen.setCook(cookInd);
                    continue;
                }

                if(rectangleName.equals("Cook"))
                {
                    Body body = makeBody(rectangle, false);
                    gameScreen.addCook(new Cook(rectangle.getWidth(), rectangle.getHeight(), body, gameScreen));
                    continue;
                }

                Rectangle normalRect = new Rectangle(rectangle);
                normalRect.setX(normalRect.getX() * PPM);
                normalRect.setY(normalRect.getY() * PPM);

                if(rectangleName.startsWith("Station")) {
                    // Stations
                    rectangleName = rectangleName.substring("Station".length()).toLowerCase();
                    Station station;
                    switch(rectangleName) {
                        case "cut":
                            station = new PreparationStation(rectangle);
                            station.setID(Station.StationID.cut);
                            gameScreen.addGameEntity(station);
                            break;
                        case "fry":
                            station = new PreparationStation(rectangle);
                            station.setID(Station.StationID.fry);
                            gameScreen.addGameEntity(station);
                            break;
                        case "counter":
                            station = new CounterStation(rectangle);
                            station.setID(Station.StationID.counter);
                            gameScreen.addGameEntity(station);
                            break;
                        case "bin":
                            station = new BinStation(rectangle);
                            station.setID(Station.StationID.bin);
                            break;
                        case "serving":
                            station = new ServingStation(rectangle);
                            station.setID(Station.StationID.serving);
                            gameScreen.addGameEntity(station);
                            this.SetServingStationPosition(station.getBody().getPosition());

                            break;
                        default:
                            station = new Station(rectangle);
                            station.setID(Station.StationID.none);
                            break;
                    }
                    gameScreen.addInteractable(station);
                }

                if (rectangleName.startsWith("Pantry")) {
                    // Pantries
                    rectangleName = rectangleName.substring("Pantry".length());
                    Pantry pantry = new Pantry(rectangle);
                    gameScreen.addInteractable(pantry);
                    switch(rectangleName) {
                        case "Lettuce":
                            pantry.setItem(FoodItem.FoodID.lettuce);
                            break;
                        case "Tomato":
                            pantry.setItem(FoodItem.FoodID.tomato);
                            break;
                        case "Onion":
                            pantry.setItem(FoodItem.FoodID.onion);
                            break;
                        case "Meat":
                            pantry.setItem(FoodItem.FoodID.meat);
                            break;
                        case "Bun":
                            pantry.setItem(FoodItem.FoodID.bun);
                            break;
                        default:
                            pantry.setItem(FoodItem.FoodID.none);
                            break;
                    }
                }
            }
        }
    }

    public void dispose() {
        tiledMap.dispose();
    }

    public Vector2 getServingStationPosition()
    {
        return ServingStationPosition;
    }
    private void SetServingStationPosition(Vector2 position)
    {
        this.ServingStationPosition = position;
    }
}
