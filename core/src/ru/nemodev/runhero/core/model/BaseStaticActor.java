package ru.nemodev.runhero.core.model;


public abstract class BaseStaticActor extends BaseActor
{
    @Override
    protected boolean isNeedUpdate()
    {
        return false;
    }
}
