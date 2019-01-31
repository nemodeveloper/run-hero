package ru.nemodev.runhero.scene.common;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.nemodev.runhero.util.InputUtils;

public class BaseScene extends Stage implements Scene
{
    private static final float MAX_WORLD_STEP = 0.25f;
    protected static final float TIME_STEP = 1.f / 60.f;

    private float accumulator;

    public BaseScene(Viewport viewport, Batch batch)
    {
        super(viewport, batch);
        this.accumulator = 0.f;
    }

    @Override
    public void init()
    {

    }

    @Override
    public void show()
    {
        setInputProcessor();
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
            accumulator += Math.min(delta, MAX_WORLD_STEP);
            while (accumulator >= TIME_STEP)
            {
                act(TIME_STEP);
                accumulator -= TIME_STEP;
            }
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

    protected boolean isInputController()
    {
        return false;
    }

    protected void setInputProcessor()
    {
        if (isInputController())
        {
            InputMultiplexer baseInput = new InputMultiplexer(InputUtils.backInputProcessor);
            baseInput.addProcessor(this);

            InputUtils.setInputProcessor(baseInput);
            InputUtils.setCatchBackButton();
        }
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
