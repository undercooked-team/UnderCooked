package core;

import Helper.MapHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import org.lwjgl.opengl.GL20;

import static Helper.Constants.PPM;


public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private MapHelper mapHelper;
    public GameScreen(OrthographicCamera camera)
    {
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.mapHelper = new MapHelper();
        this.orthogonalTiledMapRenderer = mapHelper.setupMap();
    }

    private void update()
    {
        world.step(1/60f,6,2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }
    }

    private void cameraUpdate()
    {
        camera.position.set(new Vector3(450,300,0));
        camera.update();
    }

    @Override
    public void render(float delta)
    {
        this.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();

        batch.begin();


        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }
}
