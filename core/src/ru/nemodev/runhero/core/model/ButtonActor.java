package ru.nemodev.runhero.core.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class ButtonActor extends BaseActor
{
    protected Sprite neutralState;
    protected Sprite pressState;

    public ButtonActor(Sprite neutralState, Sprite pressState)
    {
        this.neutralState = neutralState;
        this.pressState = pressState;
    }

    @Override
    protected void doDraw(Batch batch, float parentAlpha)
    {
        neutralState.draw(batch);
    }

    @Override
    public GameObject isTouch(float x, float y)
    {
        return neutralState.getBoundingRectangle().contains(x, y)
                ? this
                : null;
    }
}
