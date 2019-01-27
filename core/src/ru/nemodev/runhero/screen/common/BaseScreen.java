package ru.nemodev.runhero.screen.common;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

import ru.nemodev.runhero.scene.common.Scene;
import ru.nemodev.runhero.util.ScreenUtils;

/**
 * created by NemoDev on 06.05.2018 - 19:29
 */
public abstract class BaseScreen implements Screen
{
    private final Array<Scene> scenes;

    public BaseScreen()
    {
        this.scenes = new Array<Scene>();
    }

    public BaseScreen(Array<Scene> scenes)
    {
        this.scenes = scenes;
        for (Scene scene : scenes)
        {
            scene.init();
        }
    }

    protected final void addScene(Scene scene)
    {
        scene.init();
        scenes.add(scene);
    }

    @Override
    public void show()
    {
        for (Scene scene : scenes)
        {
            scene.show();
        }
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

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

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
