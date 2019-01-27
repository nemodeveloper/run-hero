package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ru.nemodev.runhero.entity.common.BaseActor;

public class GrassActor extends BaseActor
{
    private final Sprite grassSprite;
    private final Vector2 position;

    public GrassActor(Sprite grassSprite, Vector2 position)
    {
        this.grassSprite = grassSprite;
        this.position = position;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(grassSprite, position.x, position.y, grassSprite.getWidth(), grassSprite.getHeight());
    }
}
