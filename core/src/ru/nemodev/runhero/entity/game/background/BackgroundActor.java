package ru.nemodev.runhero.entity.game.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.entity.common.BaseStaticActor;


/**
 * created by NemoDev on 08.05.2018 - 20:04
 */
public class BackgroundActor extends BaseStaticActor implements Pool.Poolable
{
    protected final Sprite background;

    public BackgroundActor(Sprite background)
    {
        this.background = background;
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        float posX = 0;

        for (int i = 0; i < MathUtils.ceil(GameConstant.METERS_X); ++i)
        {
            drawSprite(batch, background, posX, 0.f);
            posX += 1.f;
        }
    }

    @Override
    public void reset()
    {

    }
}
