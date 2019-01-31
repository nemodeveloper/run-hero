package ru.nemodev.runhero.entity.play.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import ru.nemodev.runhero.constant.GameConstant;
import ru.nemodev.runhero.entity.common.BaseActor;
import ru.nemodev.runhero.manager.GameManager;

public class GrassActor extends BaseActor
{
    private final Array<Sprite> grassSprites;

    public GrassActor(Sprite grassSprite)
    {
        this.grassSprites = new Array<Sprite>(3);
        init(grassSprite);
    }

    private void init(Sprite grassSprite)
    {
        float direction = GameManager.getInstance().isRightDirection() ? 1.f : -1.f;

        grassSprites.add(grassSprite);
        for (int i = 1; i < 3; ++i)
        {
            Sprite lastGrass = grassSprites.get(grassSprites.size - 1);

            Sprite nextGrass = new Sprite(grassSprite);
            nextGrass.setX(lastGrass.getX() + direction * GameConstant.METERS_X);

            grassSprites.add(nextGrass);
        }
    }

    @Override
    protected void doAct(float delta)
    {
        Iterator<Sprite> grassIterator = grassSprites.iterator();
        Sprite updateSprite = null;

        while (grassIterator.hasNext())
        {
            Sprite grassSprite = grassIterator.next();
            float shift = GameManager.getInstance().isRightDirection()
                    ? -delta
                    : delta;

            grassSprite.setX(grassSprite.getX() + shift);

            if (GameManager.getInstance().isRightDirection() && grassSprite.getX() + grassSprite.getWidth() < 0.f
                    || !GameManager.getInstance().isRightDirection() && grassSprite.getX() - grassSprite.getWidth() > GameConstant.METERS_X)
            {
                updateSprite = grassSprite;
                grassIterator.remove();
            }
        }

        if (updateSprite != null)
        {
            float shift = GameManager.getInstance().isRightDirection()
                    ? updateSprite.getWidth()
                    : -updateSprite.getWidth();

            updateSprite.setX(grassSprites.get(grassSprites.size - 1).getX() + shift);
            grassSprites.add(updateSprite);
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        for (Sprite grassSprite : grassSprites)
        {
            grassSprite.draw(batch);
        }
    }
}
