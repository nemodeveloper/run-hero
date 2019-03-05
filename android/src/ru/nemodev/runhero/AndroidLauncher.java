package ru.nemodev.runhero;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class AndroidLauncher extends AndroidApplication
{
	private AndroidPlayService androidPlayService;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initFabricIO();

		setContentView(R.layout.main);

		initGameView();
		initAdb();
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

	private void initGameView()
	{
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.hideStatusBar = true;
		config.useImmersiveMode = true;

		GdxGame gdxGame = findViewById(R.id.gdxGame);

		androidPlayService = new AndroidPlayService(this);
		gdxGame.setGameView(initializeForView(new GameApp(androidPlayService), config));
	}

	private void initAdb()
	{
//		MobileAds.initialize(this, getResources().getString(R.string.ads_app_id));
//		AdView adView = findViewById(R.id.adView);
//		adView.loadAd(new AdRequest.Builder().build());
	}

	private void initFabricIO()
	{
		Fabric.with(this, new Crashlytics());
	}

}
