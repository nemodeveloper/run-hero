package ru.nemodev.runhero.core.scene;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Iterator;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.core.model.GameObject;
import ru.nemodev.runhero.core.util.InputUtils;

import static ru.nemodev.runhero.constant.GameConstant.METERS_X;
import static ru.nemodev.runhero.constant.GameConstant.METERS_Y;

public class BaseScene extends InputProcessorBase implements Scene
{
    private final Batch batch;

    public BaseScene(Batch batch)
    {
        this(new FitViewport(GameConstant.METERS_X, GameConstant.METERS_Y,
                new OrthographicCamera(GameConstant.METERS_X, GameConstant.METERS_Y)), batch);
    }

    public BaseScene(Viewport viewport, Batch batch)
    {
        super(viewport,  new Array<GameObject>(10));
        this.batch = batch;
        getCamera().setToOrtho(false, METERS_X, METERS_Y);
    }

    public void addGameObject(GameObject gameObject)
    {
        gameObject.setScene(this);
        gameObjects.add(gameObject);
    }

    @Override
    public OrthographicCamera getCamera()
    {
        return (OrthographicCamera) viewport.getCamera();
    }

    @Override
    public void show() { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void render(float delta)
    {
        update(delta);
        draw();
    }

    protected void update(float delta)
    {
        if (isNeedUpdate())
        {
            Iterator<GameObject> iterator = new Array.ArrayIterator<GameObject>(gameObjects);
            while (iterator.hasNext())
            {
                GameObject gameObject = iterator.next();
                if (gameObject.isNeedRemove())
                {
                    gameObject.remove();
                    iterator.remove();
                }
                else
                {
                    gameObject.update(delta);
                }
            }
        }
    }

    protected boolean isNeedUpdate()
    {
        return true;
    }

    protected void draw()
    {
        viewport.apply();

        Camera camera = viewport.getCamera();
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (GameObject gameObject : new Array.ArrayIterator<GameObject>(gameObjects))
        {
            gameObject.draw(batch, 1);
        }
        batch.end();
    }

    @Override
    public boolean isInputController()
    {
        return false;
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose()
    {
        InputUtils.setInputProcessor(null);
        for (GameObject gameObject : new Array.ArrayIterator<GameObject>(gameObjects))
        {
            gameObject.dispose();
        }
    }
}
