package ru.nemodev.runhero;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
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

}
