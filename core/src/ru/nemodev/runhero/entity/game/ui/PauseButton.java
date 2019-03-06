package ru.nemodev.runhero.entity.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.GameStatus;

public class PauseButton extends ImageButton
{
    public PauseButton(Drawable pauseButton, final GamePauseListener gamePauseListener, float posX, float posY, float size)
    {
        super(pauseButton);
        setBounds(posX, posY, size, size);
        getImage().setOrigin(Align.center);

        addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
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
        });
    }
}
