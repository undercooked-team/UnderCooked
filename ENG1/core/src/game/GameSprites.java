package game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/** A class which allows for easier controlling of how TextureAtlases are
 * loaded for the game, and so that they can easily be accessed.
 * Allows for avoiding using {@link TextureAtlas#createSprite(String)}
 * everywhere, which may result in problems if the Sprite isn't disposed. */
public class GameSprites {

    /**
     * An enum of the different Sprite IDs for each {@link TextureAtlas}.
     */
    public enum SpriteID {
        /** The {@link cooks.Cook}'s {@link TextureAtlas}.*/
        COOK,
        /** The {@link food.FoodItem}'s {@link TextureAtlas}.*/
        FOOD,
        /** The {@link stations.Station}'s and {@link stations.Pantry}'s {@link TextureAtlas}.*/
        STATION,
        /** The {@link customers.Customer}'s {@link TextureAtlas}.*/
        CUSTOMER

    }

    /**
     * A {@link HashMap} containing all of the {@link TextureAtlas}es for each of the
     * {@link SpriteID} IDs.
     */
    public static final HashMap<SpriteID, TextureAtlas> textureAtlases = new HashMap<>();
    static {
        textureAtlases.put(SpriteID.COOK, new TextureAtlas("atlas/cook.atlas"));
        textureAtlases.put(SpriteID.FOOD, new TextureAtlas("atlas/food.atlas"));
        textureAtlases.put(SpriteID.STATION, new TextureAtlas("atlas/station.atlas"));
        textureAtlases.put(SpriteID.CUSTOMER, new TextureAtlas("atlas/customer.atlas"));
    }

    /**
     * A {@link HashMap} of the {@link Sprite}s within the {@link TextureAtlas}es, allowing
     * each {@link Sprite} to be accessed via a simple {@link String}.
     * This is not static, or final, as it can be modified as needed during the game.
     */
    private HashMap<String, Sprite> spriteMap;

    private static GameSprites INSTANCE;

    /** Private constructor to allow for a Singleton. */
    private GameSprites() {
        this.spriteMap = new HashMap<>();
        createResources();
    }

    /**
     * The getter function to get the {@link #INSTANCE} of the {@link GameSprites}.
     * @return {@link GameSprites}: The single {@link GameSprites} instance.
     */
    public static GameSprites getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameSprites();
        }
        return INSTANCE;
    }

    /**
     * Puts together the {@link #spriteMap} using the {@link TextureAtlas}es
     * provided in the {@link #textureAtlases} {@link HashMap}.
     *
     * It creates the {@link Sprite}s here, so they
     * only ever have to be created once.
     */
    public void createResources() {
        for (SpriteID spriteID : SpriteID.values()) {
            TextureAtlas thisAtlas = textureAtlases.get(spriteID);
            for (TextureAtlas.AtlasRegion spriteRegion : thisAtlas.getRegions()) {
                spriteMap.put(spriteKey(spriteID,spriteRegion.name),thisAtlas.createSprite(spriteRegion.name));
            }
        }
    }

    /**
     * A function to convert a {@link SpriteID} and {@link Sprite} name
     * into a {@link String} key for the {@link #spriteMap}.
     * @param spriteID The {@link SpriteID} of the {@link Sprite}.
     * @param spriteName The name of the {@link Sprite} as a {@link String}.
     * @return  {@link String}: The key to use for the {@link #spriteMap}.
     */
    public String spriteKey(SpriteID spriteID, String spriteName) {
        return String.format("%s-%s", spriteID.ordinal(), spriteName);
    }

    /**
     * Get a {@link Sprite} from the {@link #spriteMap}.
     * @param spriteID The {@link SpriteID} of the {@link Sprite}.
     * @param spriteName The name of the {@link Sprite} as a {@link String}.
     * @return  {@link Sprite}: The retrieved from the {@link #spriteMap}.
     */
    public Sprite getSprite(SpriteID spriteID, String spriteName) {
        Sprite returnSprite = spriteMap.get(spriteKey(spriteID,spriteName));
        return returnSprite;
    }

    /**
     * A function to dispose of unneeded resources to free up space.
     */
    public void dispose() {
        spriteMap.clear();
    }

}
