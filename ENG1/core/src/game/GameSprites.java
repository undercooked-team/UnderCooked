package game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class GameSprites {

    public enum SpriteID {
        COOK,
        FOOD,
        STATION,
        CUSTOMER

    }

    public static final HashMap<SpriteID, TextureAtlas> textureAtlases = new HashMap<>();
    static {
        textureAtlases.put(SpriteID.COOK, new TextureAtlas("atlas/cook.atlas"));
        textureAtlases.put(SpriteID.FOOD, new TextureAtlas("atlas/food.atlas"));
        textureAtlases.put(SpriteID.STATION, new TextureAtlas("atlas/station.atlas"));
        textureAtlases.put(SpriteID.CUSTOMER, new TextureAtlas("atlas/customer.atlas"));
    }

    private HashMap<String, Sprite> spriteMap;

    static GameSprites instance;

    private GameSprites() {
        this.spriteMap = new HashMap<>();
        createResources();
    }

    public void createResources() {
        for (SpriteID spriteID : SpriteID.values()) {
            TextureAtlas thisAtlas = textureAtlases.get(spriteID);
            for (TextureAtlas.AtlasRegion spriteRegion : thisAtlas.getRegions()) {
                spriteMap.put(spriteKey(spriteID,spriteRegion.name),thisAtlas.createSprite(spriteRegion.name));
            }
        }
    }

    public String spriteKey(SpriteID spriteID, String spriteName) {
        return String.format("%s-%s", spriteID.ordinal(), spriteName);
    }

    public static GameSprites getInstance() {
        if (instance == null) {
            instance = new GameSprites();
        }
        return instance;
    }

    public Sprite getSprite(SpriteID spriteID, String spriteName) {
        Sprite returnSprite = spriteMap.get(spriteKey(spriteID,spriteName));
        return returnSprite;
    }

    public void dispose() {
        spriteMap.clear();
    }

}
