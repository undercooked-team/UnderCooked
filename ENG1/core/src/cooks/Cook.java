package cooks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

import static helper.Constants.PPM;
import food.FoodItem.FoodID;

public class Cook extends GameEntity {

    private Sprite sprite;
    /** The cook's stack of things, containing all the items they're holding. Index 0 = Top Item */
    private ArrayList<FoodID> foodStack;

    public Cook(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 10f;
        this.sprite = new Sprite(new Texture("cooks/Cook1.png"));
        this.sprite.setSize(width,height);
        this.foodStack = new ArrayList<>();
    }

    /// #################################################################################
    /// Movement and User Input

    public void userInput() {
        checkUserInput();
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(x-width/2,y-height/2);
        sprite.draw(batch);
    }

    /** Responsible for detecting user input.*/
    private void checkUserInput()
    {
        velX = 0;
        velY = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            velX += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            velX += -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            velY += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            velY += -1;
        }

        body.setLinearVelocity(velX * speed,velY * speed);

    }

    /// #################################################################################
    /// foodStack and methods

    public FoodID peekStack() {
        try {
            return foodStack.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    public FoodID popStack() {
      try {
          return foodStack.remove(0);
      } catch (IndexOutOfBoundsException e) {
          return null;
      }
    }
    public void addStack(FoodID newFood) {
        foodStack.add(0, newFood);
    }
    public ArrayList<FoodID> getStack() {
        return foodStack;
  }
    public void setStack(ArrayList<FoodID> newStack) {
        foodStack = newStack;
    }
}
