package ru.nemodev.runhero.entity.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class BaseActor extends Group
{
    @Override
    public void act(float delta)
    {
        if (isNeedUpdate())
        {
            doAct(delta);
        }

        if (hasChildren())
        {
            super.act(delta);
        }
    }

    protected boolean isNeedUpdate()
    {
        return true;
    }

    protected void doAct(float delta)
    {

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if (isVisible())
        {
            doDraw(batch, parentAlpha);
            if (hasChildren())
            {
                super.draw(batch, parentAlpha);
            }
        }
    }

    protected void doDraw(Batch batch, float parentAlpha)
    {

    }

    protected void drawSprite(Batch batch, Sprite sprite, float posX, float posY)
    {
        sprite.setPosition(posX, posY);
        sprite.draw(batch);
    }
}
