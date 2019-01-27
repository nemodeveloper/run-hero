package ru.nemodev.runhero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.ScreenManager;

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

    @Override
    public void popAndPushScreen(Screen screen)
    {
        popScreen();
        pushScreen(screen);
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
