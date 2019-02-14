package ru.nemodev.runhero;

import com.badlogic.gdx.Screen;

import ru.nemodev.runhero.manager.FontManager;
import ru.nemodev.runhero.manager.PhysicManager;
import ru.nemodev.runhero.manager.PlayServiceManager;
import ru.nemodev.runhero.manager.ResourceManager;
import ru.nemodev.runhero.screen.load.SplashScreen;
import ru.nemodev.runhero.service.PlayService;


public final class GameApp extends BaseGame
{
	public GameApp(PlayService playService)
	{
		PlayServiceManager.getInstance().setPlayService(playService);
	}

	@Override
	public void dispose()
	{
		super.dispose();

		ResourceManager.getInstance().dispose();
		PhysicManager.getInstance().dispose();
        FontManager.getInstance().dispose();
	}

	@Override
	protected Screen getStartScreen()
	{
		return new SplashScreen();
	}
}
