package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.entity.common.BaseActor;

public class StarManagerActor extends BaseActor implements Pool.Poolable
{
    private final Sprite starsSprite;

    public StarManagerActor(Sprite starsSprite)
    {
        this.starsSprite = starsSprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        starsSprite.draw(batch);
    }

    @Override
    public void reset()
    {

    }
}
