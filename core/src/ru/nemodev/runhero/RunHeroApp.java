package ru.nemodev.runhero;

import com.badlogic.gdx.Screen;

import ru.nemodev.runhero.manager.FontManager;
import ru.nemodev.runhero.manager.PhysicManager;
import ru.nemodev.runhero.manager.ResourceManager;
import ru.nemodev.runhero.screen.main.SplashScreen;


public final class RunHeroApp extends BaseGame
{
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
