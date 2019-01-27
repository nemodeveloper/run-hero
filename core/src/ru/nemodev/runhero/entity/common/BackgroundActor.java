package ru.nemodev.runhero.entity.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;


/**
 * created by NemoDev on 08.05.2018 - 20:04
 */
public class BackgroundActor extends BaseStaticActor implements Pool.Poolable
{
    protected final Sprite background;

    public BackgroundActor(Sprite background, float width, float height)
    {
        this.background = background;
        setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(background, 0.f, 0.f, getWidth(), getHeight());
    }

    @Override
    public void reset()
    {

    }
}
