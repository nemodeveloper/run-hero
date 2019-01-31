package ru.nemodev.runhero;

import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class AndroidLauncher extends AndroidApplication
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initFabricIO();
		hideSystemUI();

		setContentView(R.layout.main);

		initGameView();
		initAdb();
	}

	private void initGameView()
	{
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		GdxGame gdxGame = findViewById(R.id.gdxGame);
		gdxGame.setGameView(initializeForView(new RunHeroApp(), config));
	}

	private void initAdb()
	{
//		MobileAds.initialize(this, getResources().getString(R.string.ads_app_id));
//		AdView adView = findViewById(R.id.adView);
//		adView.loadAd(new AdRequest.Builder().build());
	}

	private void hideSystemUI()
	{
		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_IMMERSIVE
				| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN);
	}

	private void initFabricIO()
	{
		Fabric.with(this, new Crashlytics());
	}

}
