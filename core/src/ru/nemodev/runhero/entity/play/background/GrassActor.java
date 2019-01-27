package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.entity.common.BaseActor;

public class GrassActor extends BaseActor
{
    private final Sprite grassSprite;

    public GrassActor(Sprite grassSprite)
    {
        this.grassSprite = grassSprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        grassSprite.draw(batch);
    }
}
