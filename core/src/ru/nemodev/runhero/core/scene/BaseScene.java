package ru.nemodev.runhero.core.scene;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.core.model.GameObject;
import ru.nemodev.runhero.core.util.InputUtils;

public class BaseScene extends InputProcessorBase implements Scene
{
    private final Batch batch;

    public BaseScene(Viewport viewport, Batch batch)
    {
        super(viewport,  new Array<GameObject>(10));
        this.batch = batch;
    }

    public void addGameObject(GameObject gameObject)
    {
        gameObject.setScene(this);
        gameObjects.add(gameObject);
    }

    @Override
    public Camera getCamera()
    {
        return viewport.getCamera();
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
            for (GameObject gameObject : gameObjects)
            {
                if (gameObject.isNeedRemove())
                    gameObjects.removeValue(gameObject, true);
                else
                    gameObject.update(delta);
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
        for (GameObject gameObject : gameObjects)
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
        for (GameObject gameObject : gameObjects)
        {
            gameObject.dispose();
        }
    }
}
