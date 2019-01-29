package ru.nemodev.runhero.entity.common;

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
}
