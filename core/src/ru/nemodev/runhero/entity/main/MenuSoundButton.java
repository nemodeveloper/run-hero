package ru.nemodev.runhero.entity.main;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.nemodev.runhero.core.listener.SoundEventListener;
import ru.nemodev.runhero.core.manager.system.ConfigManager;
import ru.nemodev.runhero.core.model.ButtonActor;

public class MenuSoundButton extends ButtonActor
{
    final SoundEventListener soundEventListener;

    public MenuSoundButton(Sprite neutralState, Sprite pressState,
                           SoundEventListener soundEventListener)
    {
        super(neutralState, pressState);
        this.soundEventListener = soundEventListener;
    }

    @Override
    public boolean touchDown(float x, float y)
    {
        if (ConfigManager.getInstance().isEnableSound())
        {
            ConfigManager.getInstance().setEnableSound(false);
            soundEventListener.soundDisable();
        }
        else
        {
            ConfigManager.getInstance().setEnableSound(true);
            soundEventListener.soundEnable();
        }

        return true;
    }
}
