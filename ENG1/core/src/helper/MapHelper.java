package helper;
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
import game.GameScreen;

import static helper.Constants.PPM;

public class MapHelper {
    private GameScreen gameScreen;
    private TiledMap tiledMap;

    public MapHelper(GameScreen gameScreen)
    {
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
                    Body body = BodyHelper.createBody(rectangle.x + rectangle.getWidth() /2, rectangle.y +rectangle.getHeight()/2,rectangle.getWidth(), rectangle.getHeight(),false, gameScreen.getWorld());
                    int cookInd = gameScreen.addCook(new Cook(rectangle.getWidth(), rectangle.getHeight(), body));
                    gameScreen.setCook(cookInd);
                }

                if(rectangleName.equals("Cook"))
                {
                    Body body = BodyHelper.createBody(rectangle.x + rectangle.getWidth() /2, rectangle.y +rectangle.getHeight()/2,rectangle.getWidth(), rectangle.getHeight(),false, gameScreen.getWorld());
                    gameScreen.addCook(new Cook(rectangle.getWidth(), rectangle.getHeight(), body));
                }
            }
        }
    }
}
