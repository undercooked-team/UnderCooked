package game;

import Customers.Customer;
import Customers.CustomerDef;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import cooks.Cook;
import cooks.GameEntity;
import helper.CollisionHelper;
import helper.GameHud;
import helper.MapHelper;
import interactions.InputKey;
import interactions.Interactions;
import stations.CookInteractable;

import java.util.Comparator;
import java.util.PriorityQueue;

import static helper.Constants.PPM;


public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Array<Customer> customers;
    private PriorityQueue<CustomerDef> customerToSpawn;
    private long startTime = 0;
    private int secondsPassed = 0, minutesPassed = 0, hoursPassed = 0;
    private GameHud gameHud;
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private ScreenController screenController;
    // private ShapeRenderer shapeRenderer;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private MapHelper mapHelper;
    private Array<CookInteractable> interactables;
    private CollisionHelper collisionHelper;
    private GameSprites gameSprites;
    private Array<GameEntity> gameEntities;
    private DrawQueueComparator drawQueueComparator;
    private int xOffset = 480;
    private int yOffset = 320;

    //Objects
    private Array<Cook> cooks;
    private Cook cook;
    private int cookIndex;
    private int customerCount;

    public GameScreen(ScreenController screenController, OrthographicCamera camera)
    {
        this.startTime = TimeUtils.millis();
        this.cooks = new Array<>();
        this.interactables = new Array<>();
        this.collisionHelper = new CollisionHelper(this);
        this.cookIndex = -1;
        this.camera = camera;
        this.screenController = screenController;
        this.batch = screenController.getSpriteBatch();
        this.shape = screenController.getShapeRenderer();
        this.gameEntities = new Array<>();
        this.drawQueueComparator = new DrawQueueComparator();

        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.mapHelper = new MapHelper(this);
        this.orthogonalTiledMapRenderer = mapHelper.setupMap();
        this.gameHud = new GameHud(batch, this);

        customerToSpawn = new PriorityQueue<CustomerDef>();
        customers = new Array<Customer>();
    }

    public void spawnCustomer(CustomerDef cDef)
    {
        customerToSpawn.add(cDef);
    }
    public void handleCustomers()
    {
        if(!customerToSpawn.isEmpty())
        {
            CustomerDef cDef = customerToSpawn.poll();
            if(cDef.type == Customer.class)
            {
                customers.add(new Customer(this, cDef.position.x,cDef.position.y));
            }
        }
    }

    private void update(float delta)
    {

        // First thing, update all inputs
        Interactions.updateKeys();

        long diffInMillis = TimeUtils.timeSinceMillis(startTime);
        if (diffInMillis >= 1000) {
            startTime += 1000;
            secondsPassed += 1;
            if (secondsPassed >= 60) {
                secondsPassed = 0;
                minutesPassed += 1;
                if (minutesPassed >= 60) {
                    minutesPassed = 0;
                    hoursPassed += 1;
                }
            }
        }
        SpawnCustomer();
        handleCustomers();
        for(Customer customer: customers)
            customer.update(delta);

        gameHud.updateTime(hoursPassed, minutesPassed, secondsPassed);
        cameraUpdate();
        orthogonalTiledMapRenderer.setView(camera);
        batch.setProjectionMatrix(camera.combined);
        shape.setProjectionMatrix(camera.combined);
        for (Cook thisCook : cooks) {
            thisCook.getBody().setLinearVelocity(0F,0F);
            if (thisCook == cook) {
                thisCook.userInput();
            }
        }
        if(Interactions.isJustPressed(InputKey.InputTypes.COOK_SWAP)) {
            setCook((cookIndex + 1) % cooks.size);
        }
        // Not using new input system as it's for testing
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            updateCustomers();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }
        world.step(1/60f,6,2);
        for (GameEntity entity : gameEntities) {
            entity.update(delta);
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

        this.update(delta);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();
        batch.begin();

        gameEntities.sort(drawQueueComparator);

        for (GameEntity entity : gameEntities) {
            entity.render(batch);
        }

        batch.end();
        shape.begin(ShapeRenderer.ShapeType.Filled);

        for (GameEntity entity : gameEntities) {
            entity.renderShape(shape);
        }

        for (Customer customer: customers)
        {
            customer.draw(batch);
        }

        shape.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        gameHud.render();
        if(customerCount<1)
        {
            screenController.setScreen((ScreenController.ScreenID.GAMEOVER));
            ((GameOverScreen) screenController.getScreen(ScreenController.ScreenID.GAMEOVER)).setTime(hoursPassed,minutesPassed,secondsPassed);
        }
    }

    public class DrawQueueComparator implements Comparator<GameEntity> {

        @Override
        public int compare(GameEntity o1, GameEntity o2) {
            float o1Y = o1.getY(),
                  o2Y = o2.getY();
            if (o1Y > o2Y) {
                return -1;
            } else if (o2Y > o1Y) {
                return 1;
            }
            return 0;
        }
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
        gameEntities.add(newCook);
        cooks.add(newCook);
        return cooks.size-1;
    }

    public int getCustomerCount() {
        return this.customerCount;
    }
    public void updateCustomers()
    {
        if(customerCount<1)
        {
            throw new RuntimeException("Customer count should not go below 0.");
        }
        customerCount--;
        gameHud.setCustomerCount(customerCount);
    }

    public void addInteractable(CookInteractable cookInteractable) {
        interactables.add(cookInteractable);
    }

    public void addGameEntity(GameEntity entity) {
        gameEntities.add(entity);
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

    public void reset() {
        // Reset all variables
        secondsPassed = 0;
        minutesPassed = 0;
        hoursPassed = 0;
        cooks.clear();
        gameEntities.clear();
        interactables.clear();
        mapHelper.dispose();
        mapHelper = new MapHelper(this);
        world.dispose();
        this.world = new World(new Vector2(0,0), false);
        this.orthogonalTiledMapRenderer = mapHelper.setupMap();
        cookIndex = -1;
    }

    public void startGame(int customers) {
        secondsPassed = 0;
        minutesPassed = 0;
        hoursPassed = 0;
        startTime = TimeUtils.millis();
        customerCount = customers;
        gameHud.setCustomerCount(customers);
    }

    public void SpawnCustomer()
    {
        if(gameHud.GetTime() == 10)
        {
            spawnCustomer(new CustomerDef(new Vector2(mapHelper.getServingStationPosition()),Customer.class));
        }
    }

}
