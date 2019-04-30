package ru.nemodev.runhero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

		setContentView(R.layout.main);

		initAdb();
		initGameView();

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

	private void initGameView()
	{
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.hideStatusBar = true;
		config.useImmersiveMode = true;

		GdxGame gdxGame = findViewById(R.id.gdxGame);

		androidPlayService = new AndroidPlayService(this);
		gdxGame.setGameView(initializeForView(new GameApp(androidPlayService, androidAdsService), config));
	}

	private void initAdb()
	{
		AdView adView = findViewById(R.id.adView);
		androidAdsService = new AndroidAdsService(this, adView, false);
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
