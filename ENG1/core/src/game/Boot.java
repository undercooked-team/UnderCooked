package game;

// import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

  @Override
  public void create()
  {
      this.widthScreen = Gdx.graphics.getWidth();
      this.heightScreen = Gdx.graphics.getHeight();
      this.orthographicCamera = new OrthographicCamera();
      gamePort = new FitViewport(960,640, orthographicCamera);
      setScreen(new GameScreen(orthographicCamera));
  }

    public void resize(int width, int height)
    {
        gamePort.update(width, height);
    }

}
