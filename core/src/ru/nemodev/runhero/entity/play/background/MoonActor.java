package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.entity.common.BaseActor;

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
    public void draw(Batch batch, float parentAlpha)
    {
        moonSprite.draw(batch);
    }
}
