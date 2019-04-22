package ru.nemodev.runhero.entity.game.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.core.manager.GameStatus;
import ru.nemodev.runhero.core.model.ButtonActor;
import ru.nemodev.runhero.manager.GameManager;

public class PauseButton extends ButtonActor
{
    private final GamePauseListener gamePauseListener;

    public PauseButton(Sprite neutralState, Sprite pressState,
                       GamePauseListener gamePauseListener)
    {
        super(neutralState, pressState);
        this.gamePauseListener = gamePauseListener;
    }
    @Override
    public boolean touchDown(float x, float y)
    {
        if (GameManager.getInstance().isRunning())
        {
            GameManager.getInstance().setGameStatus(GameStatus.PAUSE);
            gamePauseListener.pauseStart();
        }
        else if (GameManager.getInstance().isPause())
        {
            GameManager.getInstance().setGameStatus(GameStatus.RUNNING);
            gamePauseListener.pauseEnd();
        }

        return true;
    }

}
