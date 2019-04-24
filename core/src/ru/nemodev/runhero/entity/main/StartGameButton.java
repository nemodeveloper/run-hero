package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.core.model.ButtonActor;
import ru.nemodev.runhero.core.util.InputUtils;
import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.screen.game.GameScreen;

public class StartGameButton extends ButtonActor
{
    public StartGameButton(Sprite neutralState, Sprite pressState)
    {
        super(neutralState, pressState);
    }

    @Override
    public void doTouchUp(float x, float y)
    {
        InputUtils.vibrate(250);

        GameManager.getInstance().getScreenManager().popScreen();
        GameManager.getInstance().getScreenManager().pushScreen(new GameScreen());
    }
}
