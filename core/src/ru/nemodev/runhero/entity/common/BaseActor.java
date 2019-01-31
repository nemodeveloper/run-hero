package ru.nemodev.runhero.entity.common;

import com.badlogic.gdx.scenes.scene2d.Group;

import ru.nemodev.runhero.manager.GameManager;

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
        return GameManager.getInstance().isRunning();
    }

    protected void doAct(float delta)
    {

    }
}
