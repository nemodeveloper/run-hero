package ru.nemodev.runhero.entity.load;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.core.model.BaseStaticActor;


/**
 * created by NemoDev on 08.05.2018 - 20:04
 */
public class SplashActor extends BaseStaticActor
{
    protected final Sprite background;

    public SplashActor(Sprite background)
    {
        this.background = background;
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        background.draw(batch);
    }

}
