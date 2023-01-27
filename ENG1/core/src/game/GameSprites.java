package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class GameSprites {

    public enum SpriteID {
        COOK,
        FOOD
    }

    static GameSprites instance;

    private GameSprites() {

    }

    public static GameSprites getInstance() {
        if (instance == null) {
            instance = new GameSprites();
        }
        return instance;
    }

    public static final HashMap<SpriteID, TextureAtlas> textureAtlases = new HashMap<>();
    static {
        textureAtlases.put(SpriteID.COOK, new TextureAtlas("atlas/cook.atlas"));
        textureAtlases.put(SpriteID.FOOD, new TextureAtlas("atlas/food.atlas"));
    }

    public Sprite getSprite(SpriteID spriteID, String spriteName) {
        TextureAtlas textureAtlas = textureAtlases.get(spriteID);
        return textureAtlas.createSprite(spriteName);
    }

}
