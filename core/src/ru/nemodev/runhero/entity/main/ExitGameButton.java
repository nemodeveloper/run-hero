package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import ru.nemodev.runhero.manager.GameManager;

public class ExitGameButton extends ImageButton
{
    public ExitGameButton(Drawable pauseButton, float posX, float posY, float sizeX, float sizeY)
    {
        super(pauseButton);
        setBounds(posX - sizeX / 2.f, posY, sizeX, sizeY);
        getImage().setScaling(Scaling.stretch);
        getImage().setOrigin(Align.center);

        setDebug(true);

        addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                GameManager.getInstance().exit();

                return true;
            }
        });
    }
}
