package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import ru.nemodev.runhero.entity.common.SoundEventListener;
import ru.nemodev.runhero.manager.system.ConfigManager;

public class MenuSoundButton extends ImageButton
{
    public MenuSoundButton(final Drawable enableSoundBut, final Drawable disableSoundBut,
                           final SoundEventListener soundEventListener,
                           float posX, float posY, float sizeX, float sizeY)
    {
        super(ConfigManager.getInstance().isEnableSound() ? enableSoundBut : disableSoundBut);
        setBounds(posX - sizeX / 2.f, posY, sizeX, sizeY);
        getImage().setScaling(Scaling.stretch);
        getImage().setOrigin(Align.center);

        setDebug(true);

        addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (ConfigManager.getInstance().isEnableSound())
                {
                    setStyle(new ImageButtonStyle(null, null, null, disableSoundBut, null, null));
                    ConfigManager.getInstance().setEnableSound(false);
                    soundEventListener.soundDisable();
                }
                else
                {
                    setStyle(new ImageButtonStyle(null, null, null, enableSoundBut, null, null));
                    ConfigManager.getInstance().setEnableSound(true);
                    soundEventListener.soundEnable();
                }

                return true;
            }
        });
    }
}
