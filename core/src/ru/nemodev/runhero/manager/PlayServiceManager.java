package ru.nemodev.runhero.manager;

import ru.nemodev.runhero.service.MockPlayService;
import ru.nemodev.runhero.service.PlayService;

public final class PlayServiceManager
{
    private static final PlayServiceManager instance = new PlayServiceManager();

    private PlayService playService;

    public PlayServiceManager()
    {
        this.playService = new MockPlayService();
    }

    public PlayService getPlayService()
    {
        return playService;
    }

    public void setPlayService(PlayService playService)
    {
        this.playService = playService;
    }

    public static PlayServiceManager getInstance()
    {
        return instance;
    }
}
