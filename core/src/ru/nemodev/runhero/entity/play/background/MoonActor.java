package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ru.nemodev.runhero.entity.common.BaseActor;

public class MoonActor extends BaseActor
{
    private final Sprite moonSprite;
    private final Vector2 position;

    public MoonActor(Sprite moonSprite, Vector2 position)
    {
        this.moonSprite = moonSprite;
        this.position = position;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(moonSprite, position.x, position.y, moonSprite.getWidth(), moonSprite.getHeight());
    }
}
