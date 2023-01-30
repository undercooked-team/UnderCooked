package game;

import customers.Customer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
import stations.ServingStation;

import java.util.Comparator;

import static helper.Constants.PPM;

/** A screen containing certain elements of the game. Can switch between GameScreens. */
public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private int delay;

    private long previousSecond = 0;
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
    // private GameSprites gameSprites;
    private Array<GameEntity> gameEntities;
    private DrawQueueComparator drawQueueComparator;
    private int xOffset = 480;
    private int yOffset = 320;

    //Objects
    private Array<Cook> cooks;
    private Cook cook;

    private int cookIndex;
    private int customerCount;

    private Customer customer;
    private Sprite customerIMG;

    public GameScreen(ScreenController screenController, OrthographicCamera camera)
    {
        this.previousSecond = TimeUtils.millis();
        this.cooks = new Array<>();
        this.interactables = new Array<>();
        this.collisionHelper = CollisionHelper.getInstance();
        this.collisionHelper.setGameScreen(this);
        this.cookIndex = -1;
        this.camera = camera;
        this.screenController = screenController;
        this.batch = screenController.getSpriteBatch();
        this.shape = screenController.getShapeRenderer();
        this.gameEntities = new Array<>();
        this.drawQueueComparator = new DrawQueueComparator();

        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.mapHelper = MapHelper.getInstance();
        this.mapHelper.setGameScreen(this);
        this.orthogonalTiledMapRenderer = mapHelper.setupMap();
        this.gameHud = new GameHud(batch, this);
        this.customerIMG = new Sprite(GameSprites.getInstance().getSprite(GameSprites.SpriteID.CUSTOMER,"0"));
        this.customer = new Customer(customerIMG, this);

        setupServingStation();

    }

    private void update(float delta)
    {

        // First thing, update all inputs
        Interactions.updateKeys();

        long diffInMillis = TimeUtils.timeSinceMillis(previousSecond);
        if (diffInMillis >= 1000) {
            previousSecond += 1000;
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
        if(secondsPassed==5)
        {
            customer.setCanDraw(true);
        }

        if(Interactions.isJustPressed(InputKey.InputTypes.PAUSE))
        {
            screenController.pauseGameScreen();
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

        renderGame(delta);

        if(customerCount<1)
        {
            screenController.setScreen((ScreenController.ScreenID.GAMEOVER));
            ((GameOverScreen) screenController.getScreen(ScreenController.ScreenID.GAMEOVER)).setTime(hoursPassed,minutesPassed,secondsPassed);
        }
    }

    public void renderGame(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthogonalTiledMapRenderer.render();
        batch.begin();

        gameEntities.sort(drawQueueComparator);

        for (GameEntity entity : gameEntities) {
            entity.render(batch);
            entity.renderDebug(batch);
        }
       if(customer.canSpawn())
       {
           customer.Draw(batch);
       }

        batch.end();
        shape.begin(ShapeRenderer.ShapeType.Filled);

        for (GameEntity entity : gameEntities) {
            entity.renderShape(shape);
            entity.renderShapeDebug(shape);
        }

        shape.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        gameHud.render();

    }

    /**
     * A {@link Comparator} used to compare the Y height of two
     * {@link GameEntity}s.
     * If it is negative, then the left entity is higher.
     * If it is positive, then the right entity is higher.
     * If it is 0, then both are at the same height.
     */
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

    /**
     * Get the world that the game is using.
     * @return The {@link GameScreen}'s {@link World}.
     */
    public World getWorld()
    {
        return world;
    }

    /**
     * Sets the currently active {@link #cook} that the game is using.
     * @param cookIndex The index of {@link #cook} in the {@link #cooks} array.
     * @return The {@link Cook} that the game has swapped to.
     */
    public Cook setCook(int cookIndex)
    {
        if (cookIndex < 0 || cookIndex > cooks.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.cook = cooks.get(cookIndex);
        this.cookIndex = cookIndex;
        return this.cook;
    }

    /**
     * Adds a new {@link Cook} to the {@link #cooks} {@link Array} for the game to swap between
     * @param newCook
     * @return The index of the new cook in the cooks array
     */
    public int addCook(Cook newCook) {
        gameEntities.add(newCook);
        cooks.add(newCook);
        return cooks.size-1;
    }

    /**
     * Returns the number of customers remaining before the game is finished.
     * @return The int value of {@link #customerCount}
     */
    public int getCustomerCount() {
        return this.customerCount;
    }

    /**
     * A getter to get the {@link #previousSecond}.
     * The {@link #previousSecond} is used for the timer, by checking when the previous
     * second was so that the game can check if it has been another second or not.
     * @return The {@link #previousSecond} as a {@code long}
     */
    public long getPreviousSecond() {
        return previousSecond;
    }

    /**
     * A setter to set the {@link #previousSecond} to the {@code long} provided.
     * @param newSecond What to set the {@link #previousSecond} to as a {@code long}.
     */
    public void setPreviousSecond(long newSecond) {
        previousSecond = newSecond;
    }

    /**
     * Lowers the number of customers down by 1.
     * This is called once a Customer has been successfully served.
     */
    public void updateCustomers()
    {
        if(customerCount<1)
        {
            throw new RuntimeException("Customer count should not go below 0.");
        }
        customerCount--;

        gameHud.setCustomerCount(customerCount);
    }

    /**
     * {@link #interactables} getter. Contains all the {@link #interactables} in the {@link GameScreen}.
     * @return The {@link CookInteractable} {@link Array}, {@link #interactables}.
     */
    public Array<CookInteractable> getInteractables() {
        return interactables;
    }

    /**
     * Adds a {@link CookInteractable} that a {@link Cook} can interact with.
     * @param cookInteractable The {@link CookInteractable} object that the {@link Cook}
     *                         should be able to interact with.
     */
    public void addInteractable(CookInteractable cookInteractable) {
        interactables.add(cookInteractable);
    }

    /**
     * Adds a game entity to the GameScreen to be rendered and updated.
     * @param entity The {@link GameEntity} to be added.
     */
    public void addGameEntity(GameEntity entity) {
        gameEntities.add(entity);
    }

    /** Reset the game variables, map and world. */
    public void reset() {
        // Reset all variables
        secondsPassed = 0;
        minutesPassed = 0;
        hoursPassed = 0;
        cooks.clear();
        gameEntities.clear();
        interactables.clear();
        mapHelper.dispose();
        dispose();
        mapHelper = MapHelper.newInstance();
        mapHelper.setGameScreen(this);
        world.dispose();
        this.world = new World(new Vector2(0,0), false);
        this.orthogonalTiledMapRenderer = mapHelper.setupMap();
        cookIndex = -1;
    }

    /** A variable for setting up the game when it starts. */
    public void startGame(int customers) {
        secondsPassed = 0;
        minutesPassed = 0;
        hoursPassed = 0;
        previousSecond = TimeUtils.millis();
        customerCount = customers;
        gameHud.setCustomerCount(customers);
    }

    /** Spawns a Customer. */
    public void setupServingStation()
    {
        for (ServingStation i : mapHelper.getServingStations())
        {
            i.setCustomer(this.customer);
        }
    }

    public void addnewCustomer()
    {
        this.customer = new Customer(customerIMG, this);


        customer.setCanDraw(true);
        setupServingStation();

    }



}
