package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.core.manager.system.AppServiceManager;
import ru.nemodev.runhero.core.model.ButtonActor;

public class RatingButton extends ButtonActor
{
    public RatingButton(Sprite neutralState, Sprite pressState)
    {
        super(neutralState, pressState);
    }

    @Override
    public boolean touchDown(float x, float y)
    {
        if (!AppServiceManager.getInstance().getPlayService().isSignedIn())
        {
            AppServiceManager.getInstance().getPlayService().signIn();
        }

        AppServiceManager.getInstance().getPlayService().showScore();

        return true;
    }
}
