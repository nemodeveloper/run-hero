package ru.nemodev.runhero.core.manager.resource;


import com.badlogic.gdx.audio.Music;

import ru.nemodev.runhero.core.manager.system.ConfigManager;

public final class SoundManager
{
    private static final SoundManager instance = new SoundManager();

    private SoundManager() { }

    public static SoundManager getInstance()
    {
        return instance;
    }

    public Music getMusic(String musicName, boolean loop)
    {
        Music music = ResourceLoader.getInstance().getMusic(musicName);
        music.setLooping(loop);

        return music;
    }

    public void playSound(String soundName)
    {
        if (ConfigManager.getInstance().isEnableSound())
        {
            ResourceLoader.getInstance().getSound(soundName).play();
        }
    }
}
