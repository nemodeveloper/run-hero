package ru.nemodev.runhero;

import android.app.Activity;

import ru.nemodev.runhero.core.service.AdbService;

public class AndroidAdbService implements AdbService
{
    private final Activity activity;

    public AndroidAdbService(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public void showFullScreenBanner()
    {

    }
}
