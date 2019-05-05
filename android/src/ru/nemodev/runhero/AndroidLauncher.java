package ru.nemodev.runhero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdView;

import io.fabric.sdk.android.Fabric;
import ru.nemodev.runhero.service.AndroidAdsService;
import ru.nemodev.runhero.service.AndroidPlayService;

public class AndroidLauncher extends AndroidApplication
{
	private AndroidPlayService androidPlayService;
	private AndroidAdsService androidAdsService;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initFabricIO();

		AdView adView = initAdb();
		View gameView = initGameView();

		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layout.addView(adView, params);

		setContentView(layout);
		androidAdsService.showSimpleBanner();

		hideVirtualButtons();
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		androidPlayService.onStartActivity();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		androidPlayService.onStopActivity();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		androidPlayService.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		hideVirtualButtons();
	}

	private View initGameView()
	{
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.hideStatusBar = true;
		config.useImmersiveMode = true;

		androidPlayService = new AndroidPlayService(this);

		return initializeForView(new GameApp(androidPlayService, androidAdsService), config);
	}

	private AdView initAdb()
	{
		AdView adView = new AdView(this);
		androidAdsService = new AndroidAdsService(this, adView, true);

		return adView;
	}

	private void initFabricIO()
	{
		Fabric.with(this, new Crashlytics());
	}

	private void hideVirtualButtons()
	{
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
						| View.SYSTEM_UI_FLAG_FULLSCREEN
						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}

}
