package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.core.model.ButtonActor;
import ru.nemodev.runhero.manager.GameManager;

public class ExitGameButton extends ButtonActor
{
    public ExitGameButton(Sprite neutralState, Sprite pressState)
    {
        super(neutralState, pressState);
    }

    @Override
    public void doTouchUp(float x, float y)
    {
        GameManager.getInstance().exit();
    }
}
