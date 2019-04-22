package ru.nemodev.runhero.entity.game.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.core.model.BaseActor;

public class MoonActor extends BaseActor
{
    private final Sprite moonSprite;

    public MoonActor(Sprite moonSprite)
    {
        this.moonSprite = moonSprite;
    }

    @Override
    protected void doAct(float delta)
    {
        moonSprite.rotate(0.2f);
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        moonSprite.draw(batch);
    }
}
