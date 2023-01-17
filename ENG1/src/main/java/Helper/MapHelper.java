package Helper;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapHelper {

    private TiledMap tiledMap;

    public MapHelper()
    {

    }

    public OrthogonalTiledMapRenderer setupMap()
    {
        tiledMap = new TmxMapLoader().load("Maps/StationsMap.tmx");
        return new OrthogonalTiledMapRenderer(tiledMap);
    }
}
