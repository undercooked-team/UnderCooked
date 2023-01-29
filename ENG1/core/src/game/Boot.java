package game;

// import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Boot extends Game {

  public static Boot INSTANCE;
  private int widthScreen, heightScreen;
  private OrthographicCamera orthographicCamera;
  public Boot()
  {
      INSTANCE = this;
  }
  private Viewport gamePort;
  private SpriteBatch spriteBatch;
  private ShapeRenderer shapeRenderer;
  private ScreenController screenController;

  @Override
  public void create()
  {
      this.widthScreen = Gdx.graphics.getWidth();
      this.heightScreen = Gdx.graphics.getHeight();
      this.orthographicCamera = new OrthographicCamera();
      this.spriteBatch = new SpriteBatch();
      this.shapeRenderer = new ShapeRenderer();
      this.shapeRenderer.setAutoShapeType(true);
      gamePort = new FitViewport(960,640, orthographicCamera);
      this.screenController = new ScreenController(this, orthographicCamera);
      setScreen(new MenuScreen(screenController, orthographicCamera));
  }

  public void resize(int width, int height)
    {
        gamePort.update(width, height);
    }

  public SpriteBatch getSpriteBatch() { return spriteBatch; }
  public ShapeRenderer getShapeRenderer() { return shapeRenderer; }

}
