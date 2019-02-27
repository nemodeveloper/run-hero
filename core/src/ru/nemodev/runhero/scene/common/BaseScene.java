package ru.nemodev.runhero.scene.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.util.InputUtils;

public class BaseScene extends Stage implements Scene
{
    public BaseScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void render(float delta)
    {
        doUpdate(delta);
        doDraw();
    }

    protected void doUpdate(float delta)
    {
        if (isNeedUpdate())
        {
            act(delta);
        }
    }

    protected boolean isNeedUpdate()
    {
        return true;
    }

    protected void doDraw()
    {
        getViewport().apply();
        draw();
    }

    @Override
    public boolean isInputController()
    {
        return false;
    }

    @Override
    public void resize(int width, int height)
    {
        getViewport().update(width, height, true);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        InputUtils.setInputProcessor(null);
    }
}
