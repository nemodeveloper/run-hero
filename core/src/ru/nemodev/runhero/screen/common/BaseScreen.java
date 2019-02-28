package ru.nemodev.runhero.screen.common;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.manager.GameManager;
import ru.nemodev.runhero.manager.GameStatus;
import ru.nemodev.runhero.scene.common.Scene;
import ru.nemodev.runhero.util.InputUtils;
import ru.nemodev.runhero.util.ScreenUtils;

/**
 * created by NemoDev on 06.05.2018 - 19:29
 */
public abstract class BaseScreen implements Screen
{
    private final Array<Scene> scenes;
    private final InputMultiplexer screenInput;

    private GameStatus gameStatus;

    public BaseScreen(Array<Scene> scenes)
    {
        this.scenes = scenes;
        this.gameStatus = GameStatus.UNKNOWN;
        this.screenInput = new InputMultiplexer();

        for (Scene scene : scenes)
        {
            if (scene.isInputController())
            {
                screenInput.addProcessor(scene);
            }
        }

        InputUtils.setInputProcessor(screenInput);
    }

    protected final void addScene(Scene scene)
    {
        scenes.add(scene);

        if (scene.isInputController())
        {
            screenInput.addProcessor(0, scene);
        }
    }

    protected abstract GameStatus getGameStatus();

    @Override
    public void show()
    {
        for (Scene scene : scenes)
        {
            scene.show();
        }

        GameManager.getInstance().setGameStatus(getGameStatus());
        gameStatus = GameManager.getInstance().getGameStatus();
    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clearScreen();
        for (Scene scene : scenes)
        {
            scene.render(delta);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        for (Scene scene : scenes)
        {
            scene.resize(width, height);
        }
    }

    @Override
    public void pause()
    {
        // TODO убрать когда будет экран с паузой
        gameStatus = GameManager.getInstance().getGameStatus();
        GameManager.getInstance().setGameStatus(GameStatus.PAUSE);

        for (Scene scene : scenes)
        {
            scene.pause();
        }
    }

    @Override
    public void resume()
    {
        // TODO убрать когда будет экран с паузой
        GameManager.getInstance().setGameStatus(gameStatus);
        for (Scene scene : scenes)
        {
            scene.resume();
        }
    }

    @Override
    public void hide()
    {
        for (Scene scene : scenes)
        {
            scene.hide();
        }
    }

    @Override
    public void dispose()
    {
        for (Scene scene : scenes)
        {
            scene.dispose();
        }
    }
}
