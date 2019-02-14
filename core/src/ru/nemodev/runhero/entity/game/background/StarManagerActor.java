package ru.nemodev.runhero.entity.game.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.entity.common.BaseActor;
import ru.nemodev.runhero.manager.GameManager;

public class StarManagerActor extends BaseActor implements Pool.Poolable
{
    private final Sprite starsSprite;

    public StarManagerActor(Sprite starsSprite)
    {
        this.starsSprite = starsSprite;
    }

    @Override
    protected boolean isNeedUpdate()
    {
        return GameManager.getInstance().isRunning();
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
