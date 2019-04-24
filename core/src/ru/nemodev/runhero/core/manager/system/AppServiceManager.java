package ru.nemodev.runhero.core.manager.system;

import ru.nemodev.runhero.core.service.AdbService;
import ru.nemodev.runhero.core.service.MockPlayService;
import ru.nemodev.runhero.core.service.PlayService;

public final class AppServiceManager
{
    private static final AppServiceManager instance = new AppServiceManager();

    private PlayService playService;
    private AdbService adbService;

    public AppServiceManager()
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

    public AdbService getAdbService()
    {
        return adbService;
    }

    public void setAdbService(AdbService adbService)
    {
        this.adbService = adbService;
    }

    public static AppServiceManager getInstance()
    {
        return instance;
    }
}
