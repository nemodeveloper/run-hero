package ru.nemodev.runhero.service;

import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import ru.nemodev.runhero.BuildConfig;
import ru.nemodev.runhero.R;
import ru.nemodev.runhero.core.service.AdsService;
import ru.nemodev.runhero.core.util.LogUtils;
import ru.nemodev.runhero.utils.AndroidUtils;

public class AndroidAdsService implements AdsService
{
    private static final String LOG_TAG = AndroidAdsService.class.getName();

    private final Activity activity;

    private final AdView simpleBanner;
    private InterstitialAd fullScreenBanner;

    private boolean enable;

    public AndroidAdsService(Activity activity, AdView simpleBanner, boolean enable)
    {
        this.activity = activity;
        this.simpleBanner = simpleBanner;
        this.enable = enable;

        if (this.enable)
        {
            MobileAds.initialize(activity, AndroidUtils.getString(activity, R.string.ads_app_id));
            initSimpleBanner();
            initFullScreenBanner();
        }
        else
        {
            disable();
        }
    }

    @Override
    public void showFullScreenBanner(final AdsListener adsListener)
    {
        if (!enable)
        {
            adsListener.adsShowed(true);
            return;
        }

        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (fullScreenBanner.isLoaded())
                {
                    fullScreenBanner.show();
                    fullScreenBanner.setAdListener(new AdListener(){
                        @Override
                        public void onAdFailedToLoad(int i)
                        {
                            loadNewFullscreenBanner();
                            adsListener.adsShowed(false);
                        }

                        @Override
                        public void onAdClosed()
                        {
                            loadNewFullscreenBanner();
                            adsListener.adsShowed(true);
                        }
                    });
                }
                else
                {
                    loadNewFullscreenBanner();
                    adsListener.adsShowed(false);
                }
            }
        });
    }

    @Override
    public void disable()
    {
        enable = false;
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                simpleBanner.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showSimpleBanner()
    {
        if (enable)
        {
            simpleBanner.loadAd(buildAdRequest());
        }
    }

    private AdRequest buildAdRequest()
    {
        AdRequest.Builder builder = new AdRequest.Builder();
        if (BuildConfig.DEBUG)
        {
            builder.addTestDevice(AndroidUtils.getString(activity, R.string.device_id_test));
        }

        return builder.build();
    }

    private void initSimpleBanner()
    {
        simpleBanner.setVisibility(View.VISIBLE);
        simpleBanner.setAdSize(AdSize.SMART_BANNER);
        simpleBanner.setAdUnitId(BuildConfig.DEBUG
                ? AndroidUtils.getString(activity, R.string.ads_simple_banner_id_test)
                : AndroidUtils.getString(activity, R.string.ads_simple_banner_id));
    }

    private void initFullScreenBanner()
    {
        try
        {
            fullScreenBanner = new InterstitialAd(activity);
            fullScreenBanner.setAdUnitId(BuildConfig.DEBUG
                    ? AndroidUtils.getString(activity, R.string.ads_fullscreen_banner_id_test)
                    : AndroidUtils.getString(activity, R.string.ads_fullscreen_banner_id));

            fullScreenBanner.setAdListener(new AdListener() {
                @Override
                public void onAdClosed()
                {
                    loadNewFullscreenBanner();
                }
            });

            loadNewFullscreenBanner();
        }
        catch (Exception e)
        {
            LogUtils.error(LOG_TAG, "Ошибка инициализации полноэкранного баннера", e);
        }
    }

    private void loadNewFullscreenBanner()
    {
        if (!fullScreenBanner.isLoaded())
        {
            fullScreenBanner.loadAd(buildAdRequest());
        }
    }

}
