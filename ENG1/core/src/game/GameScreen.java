package game;

import helper.Hud;
import helper.MapHelper;
import cooks.Cook;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.GL20;

import static helper.Constants.PPM;


public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Hud hud;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private MapHelper mapHelper;
    private int xOffset = 480;
    private int yOffset = 320;

    //Objects
    private Array<Cook> cooks;
    private Cook cook;
    private int cookIndex;
    public GameScreen(OrthographicCamera camera)
    {
        this.cooks = new Array<>();
        this.cookIndex = -1;
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.mapHelper = new MapHelper(this);
        this.orthogonalTiledMapRenderer = mapHelper.setupMap();
        this.hud = new Hud(batch);
    }

    private void update()
    {
        cameraUpdate();
        orthogonalTiledMapRenderer.setView(camera);
        batch.setProjectionMatrix(camera.combined);
        for (Cook thisCook : cooks) {
            thisCook.getBody().setLinearVelocity(0F,0F);
            if (thisCook == cook) {
                thisCook.userInput();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            setCook((cookIndex + 1) % cooks.size);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }
        world.step(1/60f,6,2);
        for (Cook thisCook : cooks) {
            thisCook.update();
        }
    }

    private void cameraUpdate()
    {
        camera.position.set(new Vector3(0 + xOffset,0+yOffset,0));
        camera.update();
    }

    @Override
    public void render(float delta)
    {
        this.update();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();
        hud.stage.draw();
        batch.begin();

        for (Cook thisCook : cooks) {
            thisCook.render(batch);
        }

        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }

    public World getWorld()
    {
        return world;
    }

    public Cook setCook(int cookIndex)
    {
        if (cookIndex < 0 || cookIndex > cooks.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.cook = cooks.get(cookIndex);
        this.cookIndex = cookIndex;
        return this.cook;
    }

    public int addCook(Cook newCook) {
        cooks.add(newCook);
        return cooks.size-1;
    }
}
