package ru.nemodev.runhero.core.service;

public class MockAdsService implements AdsService
{
    @Override
    public void showSimpleBanner()
    {

    }

    @Override
    public void showFullScreenBanner(AdsListener adsListener)
    {
        adsListener.adsShowed(true);
    }

    @Override
    public void disable()
    {

    }
}
