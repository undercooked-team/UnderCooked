package game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import food.FoodItem;

import java.util.HashMap;

public class GameSprites {

    public enum SpriteID {
        COOK,
        FOOD
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
