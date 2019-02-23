package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.screen.game.GameScreen;
import ru.nemodev.runhero.util.InputUtils;

public class StartGameButton extends ImageButton
{
    public StartGameButton(Drawable pauseButton,
                           float posX, float posY, float sizeX, float sizeY)
    {
        super(pauseButton);
        // TODO разобраться почему кнопка рисуется не по центру
        setBounds(posX - sizeX / 2.f, posY, sizeX, sizeY);
        getImage().setScaling(Scaling.stretch);
        getImage().setOrigin(Align.center);

        setDebug(true);

        addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                InputUtils.vibrate(250);

                GameManager.getInstance().getScreenManager().popScreen();
                GameManager.getInstance().getScreenManager().pushScreen(new GameScreen());

                return true;
            }
        });
    }
}
