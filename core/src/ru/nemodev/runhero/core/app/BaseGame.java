package ru.nemodev.runhero.core.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.core.manager.ScreenManager;
import ru.nemodev.runhero.manager.GameManager;

public abstract class BaseGame extends Game implements ScreenManager
{
    private Array<Screen> screenStack;

    protected abstract Screen getStartScreen();

    @Override
    public void create()
    {
        screenStack = new Array<Screen>();
        setGamePriority();
        GameManager.getInstance().setScreenManager(this);

        pushScreen(getStartScreen());
    }

    @Override
    public void pushScreen(Screen screen)
    {
        screenStack.add(screen);
        setScreen(screenStack.peek());
    }

    @Override
    public void popScreen()
    {
        Screen frontScreen = screenStack.pop();
        frontScreen.dispose();

        if (screenStack.size > 0)
            setScreen(screenStack.peek());
        else
            setScreen(null);
    }

    private void setGamePriority()
    {
        try
        {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
        catch (Exception e)
        {

        }
    }
}
