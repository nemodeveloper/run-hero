package ru.nemodev.runhero;

import com.badlogic.gdx.Screen;

import ru.nemodev.runhero.core.app.BaseGame;
import ru.nemodev.runhero.core.manager.resource.FontManager;
import ru.nemodev.runhero.core.manager.resource.PhysicManager;
import ru.nemodev.runhero.core.manager.resource.ResourceLoader;
import ru.nemodev.runhero.core.manager.system.AppServiceManager;
import ru.nemodev.runhero.core.service.AdsService;
import ru.nemodev.runhero.core.service.PlayService;
import ru.nemodev.runhero.screen.load.SplashScreen;


public final class GameApp extends BaseGame
{
	public GameApp(PlayService playService, AdsService adsService)
	{
		AppServiceManager.getInstance().setPlayService(playService);
		AppServiceManager.getInstance().setAdsService(adsService);
	}

	@Override
	public void dispose()
	{
		super.dispose();

		ResourceLoader.getInstance().dispose();
		PhysicManager.getInstance().dispose();
        FontManager.getInstance().dispose();
	}

	@Override
	protected Screen getStartScreen()
	{
		return new SplashScreen();
	}
}
