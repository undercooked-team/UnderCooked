package game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import helper.CollisionHelper;
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
import stations.CookInteractable;

import static helper.Constants.PPM;


public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private long startTime = 0;
    private int secondsPassed = 0;
    private Hud hud;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private MapHelper mapHelper;
    private Array<CookInteractable> interactables;
    protected CollisionHelper collisionHelper;
    private int xOffset = 480;
    private int yOffset = 320;

    //Objects
    private Array<Cook> cooks;
    private Cook cook;
    private int cookIndex;
    public GameScreen(OrthographicCamera camera)
    {
        this.startTime = TimeUtils.millis();
        this.secondsPassed = 0;
        this.cooks = new Array<>();
        this.interactables = new Array<>();
        this.collisionHelper = new CollisionHelper(this);
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
        long diffInMillis = TimeUtils.timeSinceMillis(startTime);
        if (diffInMillis >= 1000) {
            startTime += 1000;
            secondsPassed += 1;
        }

        hud.update(secondsPassed);
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            hud.UpdateCustomers();
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
        batch.begin();

        for (Cook thisCook : cooks) {
            thisCook.render(batch);
        }

        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        hud.stage.draw();
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

    public void addInteractable(CookInteractable cookInteractable) {
        interactables.add(cookInteractable);
    }

    public CollisionHelper getCollisionHelper() {
        return collisionHelper;
    }

    public Array<CookInteractable> stationCollisions(Rectangle collision) {
        Array<CookInteractable> output = new Array<>();
        for (CookInteractable cookInteractable : interactables) {
            if (collision.overlaps(cookInteractable.getRectangle())) {
                output.add(cookInteractable);
            }
        }
        return output;
    }

}
