package ru.nemodev.runhero.entity.common;


public abstract class BaseStaticActor extends BaseActor
{
    @Override
    protected boolean isNeedUpdate()
    {
        return false;
    }
}
