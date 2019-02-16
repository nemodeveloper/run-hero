package ru.nemodev.runhero.manager.system;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * created by NemoDev on 08.05.2018 - 20:45
 */
public final class ConfigManager
{
    private static final String GAME_PREFERENCES_KEY = "RUN_HERO_PREFERENCES";
    private static final String BEST_SCORE_KEY = "BEST_SCORE";

    private static volatile ConfigManager instance = new ConfigManager();

    private final Preferences gamePreferences;

    private ConfigManager()
    {
        gamePreferences = Gdx.app.getPreferences(GAME_PREFERENCES_KEY);
    }

    public static ConfigManager getInstance()
    {
        return instance;
    }

    public int getBestScore()
    {
        return gamePreferences.getInteger(BEST_SCORE_KEY, 0);
    }

    public void setBestScore(int score)
    {
        if (getBestScore() < score)
        {
            gamePreferences.putInteger(BEST_SCORE_KEY, score);
            gamePreferences.flush();
        }
    }

}
