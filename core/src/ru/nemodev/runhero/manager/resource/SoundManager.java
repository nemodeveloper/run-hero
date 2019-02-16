package ru.nemodev.runhero.manager.resource;


import com.badlogic.gdx.audio.Music;

public final class SoundManager
{
    private static final SoundManager instance = new SoundManager();

    private SoundManager() { }

    public static SoundManager getInstance()
    {
        return instance;
    }

    public Music playMusic(String musicName, boolean loop)
    {
        Music music = ResourceLoader.getInstance().getMusic(musicName);
        music.setLooping(loop);
        music.play();

        return music;
    }

    public void playSound(String soundName)
    {
        ResourceLoader.getInstance().getSound(soundName).play();
    }
}
