package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import ru.nemodev.runhero.manager.system.PlayServiceManager;

public class RatingButton extends ImageButton
{
    public RatingButton(Drawable raitingButton, float posX, float posY, float sizeX, float sizeY)
    {
        super(raitingButton);
        setBounds(posX - (sizeX / 2.f), posY, sizeX, sizeY);
        getImage().setScaling(Scaling.stretch);
        getImage().setOrigin(Align.center);

        addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (!PlayServiceManager.getInstance().getPlayService().isSignedIn())
                {
                    PlayServiceManager.getInstance().getPlayService().signIn();
                }

                PlayServiceManager.getInstance().getPlayService().showScore();

                return true;
            }
        });
    }
}
